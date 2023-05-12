# FEEDBACK FOR TEAM 4

## Total tests: 154

## Total failures: 37

### RUNNING THE TA'S TESTS ON YOUR IMPLEMENTATION
The TA's tests should not fail on your implementation. 
You can fix your implementation for the next time.
The number of failures might be higher than the number of failing tests. 
This is because a parametrized test can fail more than once.

| FAILING TESTS                                   | FIXED | NOTES              |
|-------------------------------------------------|-------|--------------------|
| example                                         | X     | notes( if needed ) |
| ProjectTest.testAddQualificationToActiveProject | x     |                    |
| ProjectTest.testConstructorNullSize             | x     |                    |
| ProjectTest.testIsHelpfulNull                   | x     |                    |
| ProjectTest.testRemoveQualifications            |       |                    |
| ProjectTest.testSetNullStatus                   | x     |                    |
| ProjectTest.testToDTO                           |       |                    | 
| ProjectTest.testToString                        |       |                    | 
| ProjectTest.testToStringWithWorkersAndStatus    |       |                    |
| QualificationTest.testConstructorBadDescription | X     |                    |
| WorkerTest.testAddNullProject                   |       |                    |
| WorkerTest.testAddNullQualification             |       |                    |
| WorkerTest.testNegativeSalary                   |       |                    |
| WorkerTest.testRemoveNullProject                |       |                    |
| WorkerTest.testRemoveQualifications             |       |                    |
| WorkerTest.testToDTO                            |       |                    |


### RUNNING YOUR TESTS ON THE TA'S IMPLEMENTATION
Your tests should not fail on the TA's implementation. If they do, 
maybe you made some incorrect assumptions.
The list of failing tests might contain methods that are not tests. T
his happens when the failure originates from those methods.
You can fix your tests for the next time.

## Total failures: 14

| FAILING TESTS                                                | FIXED  | NOTES              |
|--------------------------------------------------------------|--------|--------------------|
| example                                                      |   X    | notes( if needed ) |
| ProjectTest.testAddQualificationTwice                        |        | I don't know what the error is here.|
| ProjectTest.testAddWorkerWithNull                            |   X    |                    |
| ProjectTest.testProjectToDTO                                 |   X    |                    |
| ProjectTest.testRemoveWorkerWithNull                         |   X    |                    |
| ProjectTest.testToString                                     |   X    |                    |
| ProjectTest.testToStringWithExampleFromP1                    |   X    |                    | 
| QualificationTest.testAddWorkerWithMultipleAddition          |   X    |                    | 
| QualificationTest.testAddWorkerWithMultipleWorkersAndNull    |   X    |                    |
| QualificationTest.testAddWorkerwithNull                      |   X    |                    |
| QualificationTest.testDTOMatchMultipleWorkers                |   X    |                    |
| QualificationTest.testDTOMatchWithSingleWorkers              |   X    |                    |
| QualificationTest.testRemoveWorkerwithMultipleWorkersAndNull |   X    |                    |
| QualificationTest.testRemoveWorkerwithNull                   |   X    |                    |
| QualificationTest.testToStringEmptyString                    |   X    |                    |


### RUNNING YOUR TESTS ON THE TA'S FAULTS
Your tests detect a fault if, when running your tests on the faulty code, 
there are more failures than when runnning your tests on the TA's implementation.

## Total faults: 151
## Total missed faults: 42

| MISSED FAULTS                                      | FIXED  | NOTES              |
|----------------------------------------------------|--------|--------------------|
| example                                            |   X    | notes( if needed ) |
| Project:add_qualification_null                     |   X    |                    |
| Project:add_worker_null                            |   X    |                    |
| Project:constructor_empty_qualifications           |   X    |                    |
| Project:constructor_null_size                      |   X    |                    |
| Project:get_required_qualifications_missing        |        |                    |
| Project:remove_worker_null                         |   X    |                    | 
| Project:set_status_null                            |   X    |                    | 
| Project:to_dto_empty                               |        |                    |
| Project:to_dto_null                                |        |                    |
| Project:to_dto_size_big                            |        |                    |
| Project:to_dto_size_medium                         |        |                    |
| Project:to_dto_size_small                          |        |                    |
| Project:to_dto_status_active                       |        |                    |
| Project:to_dto_status_finished                     |        |                    |
| Project:to_dto_status_planned                      |        |                    |
| Project:to_dto_status_suspended                    |        |                    |
| Project:to_dto_wrong_missing_qualifications        |        |                    |
| Project:to_dto_wrong_name                          |        |                    |
| Project:to_dto_wrong_qualifications                |        |                    |
| Project:to_dto_wrong_workers                       |        |                    |
| Project:to_string_big_size                         |        |                    |
| Project:to_string_empty                            |   X    |                    |
| Project:to_string_medium_size                      |        |                    |
| Project:to_string_name_only                        |   X    |                    |
| Project:to_string_null                             |   X    |                    |
| Project:to_string_small_size                       |   X    |                    |
| Project:to_string_status_active                    |   X    |                    |
| Project:to_string_status_finished                  |   X    |                    |
| Project:to_string_status_planned                   |   X    |                    |
| Project:to_string_status_suspended                 |   X    |                    |
| Project:to_string_wrong_name                       |   X    |                    |
| Qualification:constructor_allows_empty_description |   X    |                    |
| Qualification:constructor_allows_null              |   X    |                    |
| Qualification:to_dto_null_workers_array            |        |                    |
| Qualification:to_dto_wrong_workers_names           |        |                    |
| Worker:add_project_allows_null                     |   X    |                    |
| Worker:add_qualification_allows_null               |   X    |                    |
| Worker:set_salary_allows negative_salary           |   X    |                    |
| Worker:to_dto_wrong_projects                       |        |                    |
| Worker:to_dto_wrong_qualifications                 |        |                    |
| Worker:to_dto_wrong_workload                       |        |                    |
| Worker:will_overload_plus_one                      |   X    |                    |
