# Writing MUnit

The steps to write a new test are as follows:

* Write tests in the form given... when... then...
* If you write your own set_up() or tear_down() ALWAYS call _super_set_up() or _super.tear_down()
* To debug a test that has just run you can use the global last_test to access the test for example to check its properties

## Unit tests vs Integration tests vs System tests
Unit tests are used to test individual components, typically engines.  They should run in isolation and should have no prerequisites. They may mock and stub other parts of the system but this should be kept to a minimum. You shoudl never replace the code that you are testing. 

Integration tests are used to test combinations of individual components

System tests are used to test that a whole workflow behaves as expected.

All tests should leave the system in the same state as before the test has run.
 ## Application and Database tests
List the available application and database test classes and when to use each

### design_manager_test_case
This will automatically create a design based on the test class name.
You can add one_time_setup_database() to create the data you need and then set_up() can look for the data in the database and populate the properties slot.  
design_manager_test_case inherits from application_system_test_mixin which has the capability to start any application.

## Use of test_aspects
When running tests interactively e.g. from the test_runner dialog, munit will compare the test's test_aspects property with the session's test_aspect setting.  If these match, the test will be run, if not it will be skipped.  Note, it may appear that the test has run as its set_up() and tear_down() will always run so it may appear to take some time. In this case, the dialog will always show the test as having passed.
The easiest way to bypass this is to empty the test's test_aspects property; for example:
* my_test_class.test_aspects.empty()

## stub_method_helper
This is a class for replacing a method with an alternative version.

If you use replace_method() the original version of the method is available as saved!method_name so you can do things like this to log what a method has done without changing its behaviour:

	_local props << .properties
	stub_method_helper.replace_method(a_class, :|method_name()|,
		_proc()
			_import props
			props[:key] << <some log data>
			_self.saved!method_name()
		_endproc)

print(stub_method_helper) will list any methods that are currently stubbed.

If you use replace_method() you must always call stub_method_helper.restore_stubbed_methods() in  your tear_down(), or, better still, call _super.tear_down() as test_case.tear_down() calls it for you.