package com.gesmallworld.xml_file_comparator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DefaultNodeMatcher;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference; 
import org.xmlunit.diff.ElementSelector;
import org.xmlunit.diff.ElementSelectors;

import com.gesmallworld.magik.commons.interop.annotations.ExemplarInstance;
import com.gesmallworld.magik.commons.interop.annotations.MagikExemplar;
import com.gesmallworld.magik.commons.interop.annotations.MagikMethod;
import com.gesmallworld.magik.commons.interop.annotations.Name;
import com.gesmallworld.magik.interop.MagikInteropUtils;

@MagikExemplar( @Name("xml_file_comparator") )
public class XmlFileComparator {

	@MagikMethod("has_differences?()")
	public static Object compareNodes(Object self , Object file1, Object file2, Object filetype, Object outputFilename) throws IOException {
		
		String filename1 = MagikInteropUtils.fromMagikString( file1 );
		String filename2 = MagikInteropUtils.fromMagikString( file2 );
		String fileType = MagikInteropUtils.fromMagikString(filetype);
		String differenceFileName = MagikInteropUtils.fromMagikString( outputFilename );
		
		
		Object[] compareResult  = compareFiles(filename1, filename2, fileType, differenceFileName);
		
		
        Object[] result = new Object[2];
        result[0] = MagikInteropUtils.toMagikBoolean( (boolean) compareResult[0] );
        if (compareResult[1] != null) {
            result[1] = MagikInteropUtils.toMagikString( (String) compareResult[1] );
        }
        return MagikInteropUtils.toMultipleResults( result );
	}

	
	
    static Object[] compareFiles(String filename1,
                                     String filename2,
                                     String fileType,
                                     String differenceFilePath) {

        Object[] result = new Object[2];
        Diff myDiff = createDiff( filename1 , filename2 , fileType);
        result[0] = myDiff.hasDifferences();
		
		if ( myDiff.hasDifferences() ){
			try{
				File outputFile = new File(differenceFilePath);
				outputFile.createNewFile();
				FileWriter writer = new FileWriter( outputFile );
				BufferedWriter bw = new BufferedWriter( writer );

				Iterable<Difference> diffs = myDiff.getDifferences();
				for (Difference d : diffs){
					bw.write( d.toString() );
					bw.newLine();
				}
				bw.close();
				writer.close();
				
				result[1] = differenceFilePath;
				
			} catch( IOException e){
				e.printStackTrace();
			}
		}
		
        return result;
    }
	
	private static Diff createDiff( String file1 , String file2 , String fileType){
				
		// This method creates the difference builder used to check if two xml files are the same.
		// This method could be extended to include a custom output format in the future
		
		/*This gml Custom ElementSelector is used to parse through a xml file which can be up to 4 layers deep.
		 * Currently it can recognise if elements are the same, even if they are not in the same order.
		 * It determines if elements are the same by comparing the Name and Text values of the FIRSTIDENTIFIER
		 * and SECONDIDENTIFIER.
		 * FIRSTELEMENTNAME and SECONDELEMENTNAME are used to customise what the parent elements of the identifiers are.
		 */
		
		final HashMap<String, String> NAMESPACES = new HashMap<String, String>();
		NAMESPACES.put("nmm", "http://www.ge.com/nmm");
		ElementSelector customElementSelector = ElementSelectors.Default;
		if ( fileType.equals("gml") ) {
			
			customElementSelector = ElementSelectors.conditionalBuilder()
					.whenElementIsNamed("DeviceMember")
					.thenUse(ElementSelectors.byXPath("./nmm:Device", NAMESPACES,
							ElementSelectors.byXPath("./nmm:ID", NAMESPACES,
									ElementSelectors.byNameAndText)))
							.whenElementIsNamed("Device")
							.thenUse(ElementSelectors.byXPath("./nmm:ID", NAMESPACES,
									ElementSelectors.byNameAndText))
							.build();
		} else if ( fileType.equals("dnom") ) {
			ElementSelector byId = ElementSelectors.byXPath( "./Id" ,ElementSelectors.byNameAndText);
			ElementSelector byID = ElementSelectors.byXPath( "./ID" , ElementSelectors.byNameAndText);
			ElementSelector byLabelPlacementType = ElementSelectors.byXPath( "./GeometryType" ,ElementSelectors.byNameAndText);
			ElementSelector byLabelPlacementText = ElementSelectors.byXPath( "./Text" ,ElementSelectors.byNameAndText);
			ElementSelector byLabelPlacementRot = ElementSelectors.byXPath( "./Rotation" ,ElementSelectors.byNameAndText);
			
			customElementSelector = ElementSelectors.and(byId,byID,byLabelPlacementType,byLabelPlacementRot,byLabelPlacementText);
		} else {
			throw new java.lang.RuntimeException("This file type is not supported by the xml comparator");
		}
		Diff myDiff = DiffBuilder.compare( Input.fromFile( file1 ) )
					.withTest( Input.fromFile( file2 ) )
					.ignoreWhitespace()
					.ignoreComments()
					.checkForSimilar()
					.withNodeMatcher( new DefaultNodeMatcher( customElementSelector, ElementSelectors.byNameAndText ))
					.build();
					
		return myDiff;
	}
	
	@ExemplarInstance
	public static Object XmlFileComparator(){
		return new XmlFileComparator();
	}
	
	@MagikMethod("new()")
	public static Object newXmlFileComparator(){
		return new XmlFileComparator();
	}
}