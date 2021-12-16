MUNIT Test Evaluation

To store results of a test run:
a_suite << a_test_class.suite()
test_runner.run(a_suite)
munit_test_storage.store_suite(a_suite, _optional a_base_directory)

default base directory: %munit_test_eval_base_directory%.default("c:\munit_test_eval")

To evaluate stored results:
munit_test_eval_framework.start(_optional test_dir_vec, test_name_vec) 

Wrapper test runner to run, store and view result

a_eval_test_runner << munit_test_eval_runner.new(_optional  test_runner, start_evaluation?)
a_eval_test_runner.run( <test class / suite / test>, _optional storage_directory, _gather suite_args)


In case of questions please contact:
uli.naedelin@hydrosconsult.de