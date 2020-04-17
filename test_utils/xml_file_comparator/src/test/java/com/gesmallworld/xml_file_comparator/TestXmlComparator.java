package com.gesmallworld.xml_file_comparator;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;


public class TestXmlComparator {

    private static final String diffFilePath = System.getProperty("java.io.tmpdir") + File.separator + "difference_file.txt";

    private void compareFiles(String refFileName, String testFileName, String fileType, boolean expectedDiff) {
        
        XmlFileComparator xf = new XmlFileComparator();
        
        String refFilepath = TestXmlComparator.class.getResource(refFileName).getPath(); 
        String testFilepath = TestXmlComparator.class.getResource(testFileName).getPath(); 

        Object[] result = xf.compareFiles(refFilepath, testFilepath, fileType, diffFilePath);
        boolean hasDifference = (boolean) result[0];
        assertEquals("Has Diff Check failed", expectedDiff, hasDifference);        
        
                
    }

    @Test
    public void test_inc01_with_extra_attribute() {
        
        compareFiles("inc01.xml", "inc01_extra_attribute.xml", "dnom",true);
    }
    @Test
    public void test_inc01_with_missing_attribute() {
        
        compareFiles("inc01.xml", "inc01_missing_attribute.xml", "dnom", true);
    }
    @Test
    public void test_inc01_with_extra_node() {
        
        compareFiles("inc01.xml", "inc01_extra_node.xml", "dnom", true);
    }
    @Test
    public void test_inc01_with_missing_node() {
        
        compareFiles("inc01.xml", "inc01_missing_node.xml", "dnom", true);
    }
    @Test
    public void test_inc01_against_inc01() {
    	
    	compareFiles("inc01.xml", "inc01.xml", "dnom", false);
    }
    @Test
    public void test_inc01_reordered() {
    	
    	compareFiles("inc01.xml", "inc01_reordered.xml", "dnom", false);
    }
    @Test
    public void test_3_layered_same_files_reordered() {
        
        compareFiles("3layersfile.xml", "3layersfile_reorder.xml", "gml", false);
    }
    @Test
    public void test_4_layered_same_files_reordered() {
        
        compareFiles("4layersfile.xml", "4layersfile_reorder.xml", "gml", false);
    }
    @Test
    public void test_4_layered_different_files_reordered() {
    	
    	compareFiles("4layersfile.xml", "4layersfile_reorder_different_text.xml", "gml", true);
    }
    @Test
    public void test_same_gml_files_reordered() {
    	
    	compareFiles("gml_file.gml", "gml_file_reordered.gml", "gml", false);
    }
    @Test
    public void test_objects_in_ui_container_reordered() {
    	
    	compareFiles("objects_in_ui_container.xml", "objects_in_ui_container_reordered.xml", "dnom", false);
    }
    @Test
    public void test_objects_in_switch_cabinet_reordered() {
    	
    	compareFiles("split_conductor_in_cabinet_by_a_switch_itest.xml", "split_conductor_in_cabinet_by_a_switch_itest_reordered.xml", "dnom", false);
    }
    
    @Test
    public void test_label_placement() {
    	
    	compareFiles("label_placement.xml", "label_placement_reordered.xml", "dnom", false);
    }
    
    
}