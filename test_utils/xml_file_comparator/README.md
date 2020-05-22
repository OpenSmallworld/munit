# XML File Comparator

A Magik interop module used for comparing and reporting differences in GML and DNOM XML files.

## Build
Before being used in a Magik session, the XML File Comparator must be built. To do this, run the following command in a terminal (from the directory this README is in):
```
ant build -Dsmallworld.gis.dir=<SMALLWORLD_GIS_DIR>
```
where `<SMALLWORLD_GIS_DIR>` is the location of the Smallworld Core product. For example `C:\smallworld\core`

### Prerequisites
* Smallworld Core: Version 5.0.0 or later and its prerequisites
* Apache Ant: Version 1.10.5
* Apache Ivy: Version 2.4.0

## Usage
### Magik Methods
* `new()`: Returns a new instance of the XML File Comparator
* `has_differences?(file1, file2, filetype, outputFilename)`: Returns two values, the first is a `_true`/`_false` value, true if the files have differences, else false. The second is the path to a file containing a list of the differences, returning `_unset` if the two files are equal
  * `file1`, `file2`: Location of the two files to compare
  * `filetype`: Either `gml` or `dnom` depending on the filetype being compared
  * `outputFilename`: The location to write the difference file to

### Example
```
# After building, ensure the product is added and module loaded
smallworld_product.add_product("<repo_root>/test_utils/xml_file_comparator")
smallworld_product.load_module(:xml_file_comparator)


comparator << xml_file_comparator.new()

# Compare GML files
has_differences? << comparator.has_differences?(<path_to_file1>.gml, <path_to_file2>.gml, "gml", "C:\temp\differences.txt")
write(has_differences?)

# Or compare DNOM files
(has_differences?, difference_file) << comparator.has_differences?(<path_to_file1>.xml, <path_to_file2>.xml, "dnom", "C:\temp\differences.txt")
write(has_differences?)
write("Differences can be viewed at: ", difference_file)
```

## Tests
The XML File Comparator tests can be loaded and run interactively as follows:
```
# After building, ensure the required modules are loaded
smallworld_product.add_product("<repo_root>/munit")
smallworld_product.add_product("<repo_root>/test_utils/xml_file_comparator")

smallworld_product.load_module(:xml_file_comparator_tests)
smallworld_product.load_module(:munit_magik_gui)

# Open test runner dialog
test_runner_dialog.open()
```