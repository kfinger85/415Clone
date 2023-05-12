# TEST CASE REPORT

## USE CASES

<br>
<hr>

### View company qualifications.

### View company employed worker.

| <h2>View Company Projects</h2>                        |
| -------------------------------------------------------- |
| <h3>test 1</h3>                                          |
| 1) have no projects in company               |
| 2) Click on the projects tab and see no projects |
|  <h3>test 2</h3> |
| 1) add a few projects to the company               |
| 3) Click on the projects tab and see the same few projects on screen|

| <h2>View Qualification Details</h2>                        |
| -------------------------------------------------------- |
| <h3>test 1</h3>                                          |
| 1) add a few qualifications to the company               |
| 2) Click on the details and notice they have no workers |
|  <h3>test 2</h3> |
| 1) add a few qualifications to the company               |
| 2) Create a worker with these qualifications |
| 3) View each of the qualification details and notice that the worker has been added |

| <h2>View Worker Details</h2>                        |
| -------------------------------------------------------- |
| <h3>test 1</h3>                                          |
| 1) add a few qualifications to the company               |
| 2) Create a worker with this qualification, a name, and a salary |
| 3) go to this worker on the workers page                 |
| 4) click on the worker                                   |
| 5) view worker details |
|  <h3>test 2</h3> |
| 1) add a few qualifications to the company               |
| 2) Create a worker with this qualification, a name, and a salary |
| 3) go to this worker on the workers page                 |
| 4) click on the worker                                   |
| 5) view worker details |
| 6) Create a Project with workers qualifications |
| 7) assign worker to this project |
| 8) view worker details and check that this project is now included |


| <h2>View Project Details</h2>                        |
| -------------------------------------------------------- |
| <h3>test 1</h3>                                          |
| 1) add a few qualifications to the company               |
| 2) Create a project with existing qualification(s), a name, and a size |
| 3) go to this project on the projects page                 |
| 4) click on the project                                   |
| 5) view correct project details |
|  <h3>test 2</h3> |
| 1) add a few qualifications to the company               |
| 2) Create a worker with this qualification, a name, and a salary |
| 3) Create a project with the same qualification                 |
| 4) click on that project and verify it shows the qualification as missing |
| 5) add the worker to the project |
| 6) click on the project and verify it shows the worker in the project |
| 7) check that it shows all qualifications are filled |


| <h2>Create new qualification</h2>                        |
| -------------------------------------------------------- |
| <h3>test 1</h3>                                          |
| 1) add a qualification to the company                    |
| 2) Check the qualification is there                      |
| 3) add another qualification                             |
| 4) Check the qualification is there                      |
| 5) add another qualification                             |
| 6) Check the qualification is there                      |
| <h3>test 2</h3>                                          |
| 1) create a duplicate qullification                      |
| = duplicate qualification should not be added to company |
| <h3>test 3</h3>                                          |
| 1) create a qualification that is all spaces.                     |
| = should not be able to add this qualification. |

| <h2>Create new worker</h2>                                                     |
| ------------------------------------------------------------------------------ |
| <h3>test 1</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a Worker with a name, the newly created qualifications, and a salary |
| 3) try to add to company                                                       |
| = worker should successfully be corrected                                      |
| <h3>test 2</h3>                                                                |
| 1) add no qualifications to the company                                        |
| 2) Create a Worker with a name and a salary                                    |
| 3) try to add to company                                                       |
| = worker should not be added to company                                        |
| <h3>test 3</h3>                                                                |
| 1) add a qualification to the company                                          |
| 2) Create a Worker with a duplicate name, chosen qualification and a salary    |
| 3) try to add to company                                                       |
| = worker should not be added to company                                        |
| <h3>test 4</h3>                                                                |
| 1) choose a few qualifications                                                 |
| 2) Create a Worker with a name, chosen qualifications and a negative salary    |
| 3) try to add to company                                                       |
| = worker should not be added to company                                        |
| <h3>test 5</h3>                                                                |
| 1) choose a few qualifications                                                 |
| 2) Create a Worker with a name which is all spaces, chosen qualifications and a positive salary    |
| 3) try to add to company                                                       |
| = worker should not be added to company                                        |

| <h2>Create new project</h2>                                                     |
| ------------------------------------------------------------------------------ |
| <h3>test 1</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a project with a name, the newly created qualifications, and a size |
| 3) try to add to company                                                       |
| 4) project should add to the projects page                                     |
| <h3>test 2</h3>                                                                |
| 1) add no qualifications to the company                                        |
| 2) Create a project with a name and a size                                    |
| 3) try to add to company                                                       |
| 4) create project button should be disabled                                        |
| <h3>test 3</h3>                                                                |
| 1) add a qualification to the company                                          |
| 2) Create a project with a duplicate name, chosen qualification and a size    |
| 3) try to add to company                                                       |
| 4) create project button should be disabled                                        |
| <h3>test 4</h3>                                                                |
| 1) add a qualification to the company                                                 |
| 2) Create a project with a name, chosen qualifications and and dont pick a size    |
| 3) create project button should be disabled         
| <h3>test 5</h3>                                                                |
| 1) add a qualification to the company                                                 |
| 2) Create a project with chosen qualifications, a size and dont type a name   |
| 3) create project button should be disabled   |
| <h3>test 6</h3>                                                                |
| 1) add a qualification to the company                                                 |
| 2) Create a project with chosen qualifications, a size and all spaces as a name  |
| 3) create project button should be disabled   |
### Assign worker.

### Unassign worker.

| <h2>Start</h2>                                                                        |
| ------------------------------------------------------------------------------------------------- |
| <h3>Test 1</h3>                                                                                   |
| --company has no qualifications to start---                                                       |
| 1) create 3 qualifications in the company                                                         |
| 2) create a worker with these 3 qualifications                                                    |
| 3) create a project whose requiired qualifications are these 3 qualifications                     |
| 4) assign worker to project                                                                       |
| 5) start a project                                                                                |
| = project should be in active mode                                                                |
| <h3>Test 2</h3>                                                                                   |
| --company has no qualifications to start---                                                       |
| 1) create 5 qualifications in the company                                                         |
| 2) create two workers with 2 qualifications each but different from the other                     |
| 3) create a project whose requiired qualifications are these 5 qualifications                     |
| 4) assign both workers to project                                                                 |
| 5) start a project                                                                                |
| = project should not be able to be started                                                        |
| <h3>Test 3</h3>                                                                                   |
| --company has no qualifications to start---                                                       |
| 1) create 4 qualifications in the company                                                         |
| 2) create two workers with 2 qualifications each that contain all the qualifications just created |
| 3) create a project whose requiired qualifications are these 4 qualifications                     |
| 4) assign both workers to project                                                                 |
| 5) start a project                                                                                |
| 6) unassign a worker                                                                              |
| = project should change to SUSPENDED state                                                        |
| <h3>Test 4</h3>                                                                                   |
| --company has no qualifications to start---                                                       |
| 1) create 4 qualifications in the company                                                         |
| 2) create one worker_one with 1 qualification and worker_2 with all 4 qualifications              |
| 3) create a project whose requiired qualifications are these 4 qualifications                     |
| 4) assign worker_one first                                                                        |
| 5) assign worker_two second                                                                       |
| 6) start a project                                                                                |
| 7) unassign worker_one                                                                            |
| = project should remain in ACTIVE state                                                           |
| 8) unassign worer_two                                                                             |
| 9) project should change to SUSPENDED state                                                       |

| <h2>Finish Project</h2>                                                     |
| ------------------------------------------------------------------------------ |
| <h3>test 1</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a project with a name, the newly created qualifications, and a size |
| 3) add to company                                                       |
| 4) try to finish project                                   |
| 5) either nothing or an error message should happen                                   |
| <h3>test 2</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a project with a name, the newly created qualifications, and a size |
| 3) add to company                                                       |
| 4) create workers with those qualifications                                   |
| 5) add those workers to the project                                   |
| 6) try to finish project                                         |
| 7) either nothing or an error message should happen                 |
| <h3>test 3</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a project with a name, the newly created qualifications, and a size |
| 3) add to company                                                       |
| 4) create workers with those qualifications                                   |
| 5) add those workers to the project                                   |
| 6) start the project                                   |
| 7) try to finish project                                         |
| 8) project should show finished in details                |
| <h3>test 4</h3>                                                                |
| 1) add a few qualifications to the company                                     |
| 2) Create a project with a name, the newly created qualifications, and a size |
| 3) add to company                                                       |
| 4) create workers with those qualifications                                   |
| 5) add those workers to the project                                   |
| 6) start the project                                   |
| 7) try to finish project                                         |
| 8) try to finish project                               |
| 8) nothing or an error message should show                             |

## WorkFlows

### Start Up

> In this workflow we are starting from scratch. We first head over to the Projects tab and create a new project, with all new qualifications.
> We now have to head over to the Workers page in order to create new workers since there will not be any in the company with these new qualifications.
> We create the workers needed. We then head over to the projects tab and click on our new project. We see all of the details and add the new workers.
> We are now able to start the new project.

### Out of Funds
> In this workflow we blow through our runway in a week and run out of money. To do this, we first head over to the projects tab and click on our doomed but active project. 
> We then unassign all of the workers from the project. Once the qualification requirments are no longer met, the project is automatically suspended. We keep
> unassigning until all of the workers are unassigned.

### Angel Investor
> In this workflow we once again spend all of our money on private chefs and ping pong tables but we are saved at the last minute by an angel investor.
> We click on the projects tab and then click on our previously suspended project. We re-assign all of the workers who were previously un-assigned. 
> Once the qualification requirment is met, and the workers are re-assigned, we manually start the project back up.
