# Getting Started

## Installation

### Get The Source

Either clone the repository to a local directory

```
$ git clone https://github.com/OpenSmallworld/munit.git
```

or download a zip of the source from [https://github.com/OpenSmallworld/munit](https://github.com/OpenSmallworld/munit) and extract that to a local directory.

### Loading the Source

```
smallworld_product.add_product("\path\to\sw_dev_tools")
smallworld_product.add_product("\path\to\munit")
sw_module_manager.load_module(:munit_magik_gui)
```

### Example

```
_pragma(classify_level=debug, topic={test})
def_slotted_exemplar(
	:example_test, {}, :test_case)
$

_pragma(classify_level=debug, topic={test})
_method example_test.set_up ()
	## 
	## 
	
_endmethod
$

_pragma(classify_level=debug, topic={test})
_method example_test.tear_down ()
	## 
	## 
	
_endmethod
$

_pragma(classify_level=debug, topic={test})
_method example_test.test_1()
	## 
	## 

	_self.assert_true(_true)
	
_endmethod
$

_pragma(classify_level=debug, topic={test})
_method example_test.test_2()
	## 
	## 

	_self.assert_false(_false)
	
_endmethod
$

_pragma(classify_level=debug, topic={test})
_method example_test.test_3()
	## 
	## 

	expected_result << hash_table.new_with(:a,"b",:c,"d")
	actual_result << hash_table.new_with(:a,"b",:c,"d")

	_self.assert_deep_equals(expected_result, actual_result)
	
_endmethod
$

_pragma(classify_level=debug, topic={test})
_method example_test.test_fail()
	## 
	## 

	_self.assert_false(_true) # deliberately fail
	
_endmethod
$
```

### Running a test

```
Magik> test_runner.run(example_test)

Magik> ...F.

Time:  7 mseconds 
There was 1 failure:

1) test_fail() [user:example_test] : 'True is False'

FAILURES!!!
Tests run: 4,  Assertions: 4,  Failures: 1,  Errors: 0
MagikSF> 

Magik> 
```

### Test Runner dialog

```
test_runner_dialog.open()
```