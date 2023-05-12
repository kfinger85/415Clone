# FEEDBACK FOR TEAM 4

## Total tests: 214

### RUNNING THE TA'S TESTS ON YOUR IMPLEMENTATION
The TA's tests should not fail on your implementation.
You can fix your implementation for the next time.
The number of failures might be higher than the number of failing tests.
This is because a parametrized test can fail more than once.


## Total failures: was 5, now 9

| FAILING TESTS                                                | FIXED | NOTES              |
|--------------------------------------------------------------|-------|--------------------|
| example                                                      |   X   | notes( if needed ) |
| CompanyTest.testAssignActiveProject                          |   X   | maybe fixed this   |
| CompanyTest.testCreateProjectWithQualificationsNotInCompany  |   X   |                    | 
| CompanyTest.testUnassignNullAll Expected exception           |       |                    |
| ProjectTest.testAddQualificationToActiveProject              |       |                    |
| ProjectTest.testRemoveQualifications                         |       |                    |
| CompanyTest.testStartInvalidStatus                           |       |                    |
| CompanyTest.testStartMissingQualificationsUnassigned         |       |                    |
| CompanyTest.testWronglyUnassign                              |   X   |                    |
| WorkerTest.testRemoveQualifications                          |       |                    |

### RUNNING YOUR TESTS ON THE TA'S IMPLEMENTATION
Your tests should not fail on the TA's implementation. If they do,
maybe you made some incorrect assumptions.
The list of failing tests might contain methods that are not tests. T
his happens when the failure originates from those methods.
You can fix your tests for the next time.

## Total failures: 13 now 24

| FAILING TESTS                                                                                | FIXED | NOTES              |
|----------------------------------------------------------------------------------------------|-------|--------------------|
| example                                                                                      |   X   | notes( if needed ) |
| CompanyTest.testAssignAndUnassignCorrectlyMultipleCompanies:1112 » IllegalArgument           |       |                    |
| CompanyTest.testAssignCorrectlyAssignsWorkerFromAvailableToAssigned:395 » IllegalArgument    |       |                    |
| CompanyTest.testAssignIsHelpful:452 » IllegalArgument                                        |       |                    |
| CompanyTest.testAssignWorkerAlreadyInProject Expected exception: java.lang.Exception         |       |                    |
| CompanyTest.testCompanyCreateWorkerExpected:821 expected:<[]> but was:<[BoB:0:1:10]>         |       |                    |
| CompanyTest.testFinishedWrongStatusFinished Expected exception: java.lang.Exception          |       |                    |
| CompanyTest.testFinishedWrongStatusSuspended Expected exception: java.lang.Exception         |       |                    |
| CompanyTest.testStartWrongStatusActive Expected exception: java.lang.Exception               |       |                    |
| CompanyTest.testUnAssignAssignedDoesNotContainWorker Expected exception: java.lang.Exception |       |                    |
| CompanyTest.testUnassignALLCorrectlyUnassignsMultiple:1279 » IllegalArgument                 |       |                    |
| CompanyTest.testUnassignWorkerIsNullProjectIsValid »  Unexpected exception, ex...            |       |                    |
| CompanyTest.testgetUnavailableEmpty:1224 » IllegalArgument                                   |       |                    |

| CompanyTest.testCreateQualificationThatCompanyAlreadyHas                                     |       |                    |
| CompanyTest.testStartNotFulfilled                                                            |       |                    |
| CompanyTest.testAssignAndUnassignMultipleCompaniesThrowingExceptions                         |       |                    |
| CompanyTest.testgetUnassignedEmpty                                                           |       |                    |
| CompanyTest.testAssignWorkerWillOverloadProject                                              |       |                    |
| CompanyTest.testCompanyCreateProjectNormal                                                   |       |                    |
| CompanyTest.testFinishedWrongStatusPlanned                                                   |       |                    |
| CompanyTest.testStartWrongStatusFinished                                                     |       |                    |
| CompanyTest.testUnassignALLCorrectlyUnassignsOne                                             |       |                    |
| CompanyTest.testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications       |       |                    |
| ProjectTest.testAddQualificationTwice Expected exception: java.lang.IllegalArgumentException |       |                    |
| ProjectTesttestAddQualificationActiveProject                                                 |       |                    | 

| ProjectTest.testAddQualificationTwice Expected exception: java.lang.IllegalArgumentException |   X   |  deleted the test  |



### RUNNING YOUR TESTS ON THE TA'S FAULTS
Your tests detect a fault if, when running your tests on the faulty code,
there are more failures than when runnning your tests on the TA's implementation.


# NEW FAULT REPORT
## Total faults: 214
## Total missed faults: 19

| MISSED FAULTS                                        | FIXED | NOTES              |
|------------------------------------------------------|-------|--------------------|
| Company:assign_no_status_check                       |       |                    |
| Company:assign_no_workload_check                     |       |                    |
| Company:create_project_allows_unknown_qualifications |       |                    |
| Company:finish_no_status_check                       |       |                    |
| Company:get_employed_workers_empty                   |       |                    |
| Company:get_employed_workers_null                    |       |                    |
| Company:get_projects_empty                           |       |                    |
| Company:get_projects_null                            |       |                    |
| Company:get_unassigned_workers_all                   |       |                    |
| Company:get_unavailable_workers_all                  |       |                    |
| Company:start_no_qualifications_check                |       |                    |
| Company:start_no_status_check                        |       |                    |
| Company:unassign_all_removes_one                     |       |                    |
| Company:unassign_allows_unknown_worker               |       |                    |
| Project:constructor_empty_qualifications             |       |                    |
| Project:to_dto_size_big                              |       |                    |
| Project:to_dto_status_planned                        |       |                    |
| Project:to_dto_wrong_missing_qualifications          |       |                    |
| Worker:to_dto_wrong_workload                         |       |                    |


# OLD FAULT REPORT
## Total faults: 214
## Total missed faults: 79

| MISSED FAULTS                                        | FIXED | NOTES              |
|------------------------------------------------------|-------|--------------------|
| example                                              |   X   | notes( if needed ) |
| Company:assign_allows_unknown_project                |       |                    |
| Company:assign_allows_unknown_worker                 |       |                    |
| Company:assign_no_helpful_check                      |       |                    |
| Company:assign_no_status_check                       |       |                    |
| Company:assign_no_workload_check                     |       |                    |
| Company:create_project_allows_unknown_qualifications |       |                    |
| Company:create_worker_allows_unknown_qualifications  |       |                    |
| Company:equals_compares_to_strings                   |   X   |                    |
| Company:finish_allows_unknown_project                |       |                    |
| Company:finish_no_status_check                       |       |                    |
| Company:get_assigned_workers_all                     |       |                    |
| Company:get_available_workers_all                    |       |                    |
| Company:get_available_workers_null                   |       |                    |
| Company:get_employed_workers_empty                   |       |                    |
| Company:get_employed_workers_null                    |       |                    |
| Company:get_projects_empty                           |       |                    |
| Company:get_projects_null                            |       |                    |
| Company:get_qualifications_empty                     |       |                    |
| Company:get_qualifications_null                      |       |                    |
| Company:get_unassigned_workers_all                   |       |                    |
| Company:get_unassigned_workers_null                  |       |                    |
| Company:get_unavailable_workers_all                  |       |                    |
| Company:get_unavailable_workers_available            |       |                    |
| Company:get_unavailable_workers_empty                |       |                    |
| Company:get_unavailable_workers_null                 |       |                    |
| Company:start_allows_unknown_project                 |       |                    |
| Company:start_no_checks                              |       |                    |
| Company:start_no_qualifications_check                |       |                    |
| Company:start_no_status_check                        |       |                    |
| Company:unassign_all_does_nothing                    |       |                    |
| Company:unassign_all_removes_one                     |       |                    |
| Company:unassign_allows_unknown_project              |       |                    |
| Company:unassign_allows_unknown_worker               |       |                    |
| Company:unassign_no_checks                           |       |                    |
| Company:unassign_no_status_check                     |       |                    |
| Project:add_qualification_null                       |   X   |                    |
| Project:add_worker_null                              |   X   |  we already had a test for this|
| Project:constructor_empty_qualifications             |       |                    |
| Project:equals_compares_to_strings                   |   X   |                    |
| Project:equals_false                                 |       |                    |
| Project:remove_worker_null                           |   X   |  we already had a test for this|
| Project:set_status_null                              |       |                    |
| Project:to_dto_empty                                 |       |                    |
| Project:to_dto_null                                  |       |                    |
| Project:to_dto_size_big                              |       |                    |
| Project:to_dto_size_medium                           |       |                    |
| Project:to_dto_size_small                            |       |                    |
| Project:to_dto_status_active                         |       |                    |
| Project:to_dto_status_finished                       |       |                    |
| Project:to_dto_status_planned                        |       |                    |
| Project:to_dto_status_suspended                      |       |                    |
| Project:to_dto_wrong_missing_qualifications          |       |                    |
| Project:to_dto_wrong_name                            |       |                    |
| Project:to_dto_wrong_qualifications                  |       |                    |
| Project:to_dto_wrong_workers                         |       |                    |
| Project:to_string_small_size                         |       |                    |
| Project:to_string_status_planned                     |       |                    |
| Qualification:equals_compares_to_strings             |   X   |                    |
| Qualification:to_dto_null_workers_array              |       |                    |
| Worker:add_project_allows_null                       |   X   |                    |
| Worker:add_qualification_allows_null                 |   X   |                    |
| Worker:constructor_allows_negative_salary            |       |                    |
| Worker:equals_compares_to_strings                    |   X   |                    |
| Worker:get_name_wrong_name                           |       |                    |
| Worker:is_available_plus_one                         |       |                    |
| Worker:set_salary_allows negative_salary             |       |                    |
| Worker:to_dto_empty                                  |       |                    |
| Worker:to_dto_null                                   |       |                    |
| Worker:to_dto_wrong_name                             |       |                    |
| Worker:to_dto_wrong_projects                         |       |                    |
| Worker:to_dto_wrong_qualifications                   |       |                    |
| Worker:to_dto_wrong_salary                           |       |                    |
| Worker:to_dto_wrong_workload                         |       |                    |
| Worker:to_string_salary_double                       |       |                    |
| Worker:to_string_wrong_name                          |       |                    |
| Worker:to_string_wrong_projects                      |       |                    |
| Worker:to_string_wrong_qualifications                |       |                    |
| Worker:to_string_wrong_salary                        |       |                    |
| Worker:will_overload_same_project                    |       |                    |
