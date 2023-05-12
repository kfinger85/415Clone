## 1) View company qualifications. (3 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View company qualifications||
|  Summary: user clicks on qualifications tab to view qualifications in company||
|  Actor: User||
|  Precondition: company has qualifications||
|  User clicks on qualifications tab and views the qualifications | qualifications page opens |
|  User views company's list of qualifications| page has displayed an easy way for user to view list of qualifications |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user goes to qualifications page and there are no qualifications | page should have no qualifications |


Postcondition: user leaves qualifications page

<br>

## 2) View company employed worker. (3 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View company workers||
|  Summary: user clicks on worksrs tab to view workers in company||
|  Actor: User||
|  Precondition: company has workers||
|  User clicks on workers tab and views the workers | workers page opens |
|  User views company's list of workers | page has displayed an easy way for user to view list of workers |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user goes to workers page and there are no workers | page should have no workers |

Postcondition: user leavers workers page

<br>

## 3) View company projects. (3 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View company projects||
|  Summary: user clicks on projects tab to view projects in company||
|  Actor: User||
|  Precondition: company has projects||
|  User clicks on projects tab | projects page opens |
|  User views company's list of projects| page displays a list of project names in the company |
#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| User clicks on projects tab and there are no projects | page should display no projects |

<br>

## 4) View qualification details. (Given a specific qualification, the details include description and workers that have that particular qualification.) (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View qualification details||
|  Summary: user clicks on a qualification to view worker details||
|  Actor: User||
|  Precondition: user is on qualification page and company has a few qualifications||
|  User clicks on a qualification | page opens up the qualification details |
|  user looks for the workers that have this qualificaation | page displays the list of workers with the qualification |
|              |                 |
#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| Company currently has no qualifications | page qill display no qualifications |
| user clicks on a qualification that has no workers |  page will display no workers with this qualificaiton |
|              |                 |

PostCondition: User leaves the page

<br>

## 5) View worker details. (Given a specific worker, the details include name, salary, current workload value, projects they are assigned to, and their qualifications.) (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View Worker Details||
|  Summary: user clicks on a worker to view worker details||
|  Actor: User||
|  Precondition: Workers page is loaded||
|  User views the workers | page has displayed an easy way for user to view list of workers |
|  User looks for workers name |  Page will initially only show the workers names |
|  user clicks on worker | when user clicks the details should open up |
|  user checks for salary | prage displays worker's salary |
|  user checks for projects | page display 1 or more projects for worker|
|  user checks for workload | page displays workload |
|  user checks for qualifications | page displays 1 or more qualifications |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user searches for a worker not in company | page will show 'worker not found' |
| user notices the worker has no projects | This is correct if worker has not been assigned |
| user notices the worker has no workload | This is correct if worker has not been assigned |
| user decides to assign/unassign worker from a project | page offers a way to direct the user for this task|
| user would like to go to another page | page displays different tabs for worker to go to |

PostCondition: user leaves workers page

<br>

## 6) View project details. (Given a specific project, the details include name, size, status, assigned employees, required qualifications, and missing qualifications. Missing qualifications can be visualized by color coding the required qualifications: e.g., red = missing, green = satisfied.) (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: View Project Details||
|  Summary: user clicks on a project to view its details||
|  Actor: User||
|  Precondition: Projects page is loaded and has > 1 project||
|  User looks for project's name |  Page will initially only show list of project names |
|  user clicks on projects | when user clicks a project details pop up |
|  user checks for name | currently clicked on project still has a visible name|
|  user checks for size | size of project is displayed in text|
|  user checks for assigned employees | list of assigned employees to that project is visible|
|  user clicks on an assigned employee | user is sent to workers page |
|  user checks for required qualifications | page displays all required qualifications |
|  user checks for missing qualifications | if project has any missing qualifications they are displayed under missing qualifications |
|  user clicks on a qualification | user is sent to qualifications page |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user searches for a worker not in company | page will show 'worker not found' |
| user notices the worker has no projects | This is correct if worker has not been assigned |
| user notices the worker has no workload | This is correct if worker has not been assigned |
| user decides to assign/unassign worker from a project | page offers a way to direct the user for this task|
| user would like to go to another page | page displays different tabs for worker to go to |

<br>

## 7) Create new qualification. (This new qualification should become visible via use case 1.) (3 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Create Qualification||
|  Summary: user views Qualification tab and looks at Qualifications||
|  Actor: User||
|  Precondition: Qualifications page is loaded||
|  user decides to create a new qualification |  page should have a create worker button and opens form when clicked |
|  user types in a new qualification | page should have a way for user to enter name of qualification |
|  user has entered all valid input | page should give clues that input is valid and a 'create' button can be clicked|

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user enters duplicate qualification | page should not let user add to company |
| user tries to add a new qualification without any input.| page should not let user create |
| user goes to wrong tab to enter qualification | if the user is in the worker, projects, or qualification tab, they will be able to create a new qualification |
|||

PostCondition: User successfully added a new qualification to the company.

<br>

## 8) Create new worker. (This new worker should become visible via use case 2.) (5 points)
#### Typical Course of Action

| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Create A Worker||
|  Summary: user views the 'Workers' tab and decides to create a new worker.||
|  Actor: User||
|  Precondition: Workers page is loaded||
|  user goes to workers tab      |  workers page opens up |
|  user decides to create a new worker |  page should have a create worker button and opens form when clicked |
|  user adds worker name | page should have a way for user to enter name |
|  user adds salary | page should have a way for user to enter salary |
|  user chooses qualifications | page should have a way for user to choose at least 1 or multiple qualifications for this worker |
|  user has entered all valid input | page should give clues that input is valid and a 'create' button can be clicked|

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user tries to Create a worker and no qualifications in company | user must create at least 1 qualification |
| user wants to create a worker with a qualification not in company | user must create a new qualification |
| user enters duplicate name | page will not create a new worker and let the user know of the problem |
| user tries to create a worker without a name | page will not create a new worker and let the user know of the problem  |
| user tries to create a worker without a salary | page will not create a new worker and let the user know of the problem |
| user tries to create a worker without a qualification | page will not create a new worker and let the user know of the problem |

PostCondition: New worker is created and added to the Company's Database.

<br>

## 9) Create new project. (This new project should become visible via use case 3.) (5 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Create A Project||
|  Summary: user views the Project tab and decides to create a new project.||
|  Actor: User||
|  Precondition: Project page is loaded and currently open||
|  user decides to create a new project |  page has a create project button and opens form when clicked |
|  user adds project name | page should have a way for user to enter name |
|  user adds size | page should have a way for user to enter a project size |
|  user chooses qualifications | page should have a way for user to choose at least 1 or multiple qualifications for this project |
|  user has entered all valid input | page should give clues that input is valid and a 'create' button can be clicked|

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| user tries to Create a project and no qualifications in company | user must create at least 1 qualification |
| user wants to create a project with a qualification not in company | user must create a new qualification |
| user enters duplicate project name | page will not create a new project and let the user know of the problem |
| user tries to create a project without a name | page will not create a new project  |
| user tries to create a project without/with improper size | page will not create a new project|
| user tries to create a project without a qualification | page will not create a new project |

## 10) Assign worker. (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Assign Worker||
|  Summary: User assigns a worker to a project.||
|  Actor: User||
|  Precondition: Worker exists \| project exists \| worker's qualifications match project's \| project needs workers.||
|  User clicks on Projects tab. |  Projects page is displayed. |
|  User clicks on project that they want to assign worker to.            |      The project in question's details are displayed.           |
|  User clicks on blue person with + sign            |      The assign worker modal is opened.           |
|  User clicks on the worker they wish to assign.       |   The worker in question is assigned to the project, this information is displayed to the user.        |
|              |                 |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|       User wants to assign worker that doesn't exist yet so they first click on the  workers tab.     |     Workers tab is opened.            |
|       The user clicks on "Create A Worker" button.       |       The "Create A Worker" modal is opened.          |
|       The user inputs valid data for all fields.       |         The Create Worker button becomes activated.        |
|       The user clicks the "Create Worker" button.       |        The worker is created, this information is displayed to the user.         |
|       The user heads back over to the Projects tab.       |      The Projects tab opens up.           |
|       The user continues with the primary use case.       |      The system continues with the primary use case.           |

## 11) Unassign worker. (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Unassign Worker||
|  Summary: User unassigns a worker from a project.||
|  Actor: User||
|  Precondition: Worker exists \| project exists \| worker is assigned to project.||
|  User clicks on the "Projects" tab.           |      The "Projects" tab is opened.           |
|  User clicks on the project in question.            |        The project in question's details are displayed         |
|  User clicks on red person with - sign            |       The unassign worker modal is opened.          |
|  User clicks on the worker they wish to unassign.            |       The worker is unassigned. If this causes the company to change state, this new state is reflected in the company details. The worker is removed from the Workers list.          |
|              |                 |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  The project doesn't have any workers assigned. So, the user first assigns some workers            |     The system responds according to the assign worker use case.            |
|  The user accidentally assigned the wrong worker. So, they decide the unassign this worker and follow the primary use case..            |    The system responds according to the primary use case.             |
|              |                 |

## 12) Start project. (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Start Project||
|  Summary: user views Project Page and starts a Project.||
|  Actor: User||
|  Precondition: User is on the Project page||
|  User views project Page and wants to start a project |  page should display a way for user to start a project |
|  User Clicks on project |  opens up details about the project |
|  User looks at details and sees the project can be started | page displays visual clues for the user to know when/what projects can be started |
| user clicks on the start button | page displays a start button |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| User tries to start a finished project | user can't start project |
| user tries to start a suspended project | user can only start project if qualifications are met |
| user tries to start a planned project | use can only start project if qualifications are met |
| user tries to start an active Project | no changes to the project status |
| user does not want to start project but wants to see what projects are available to be started | page displays a list of available projects with a button click |

PostCondition: project is started

## 13) Finish project. (4 points)
#### Typical Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
|  Use Case Name: Finish Project||
|  Summary: user views an active Project Page and finishes the Project.||
|  Actor: User||
|  Precondition: User is on the Project page||
|  User views project Page and wants to finish a project |  page should display a way for user to finish a project |
|  User Clicks on project |  opens up details about the project |
|  User looks at details and sees the project can be finished | page displays visual clues for the user to know when/what projects can be finished |
| user clicks on the finish button | page displays a finish button |

#### Alternate Course of Action
| Actor/Action | System Response |
|--------------|-----------------|
| User tries to finish a finished project | user can't finish project |
| user tries to finish a suspended project | user can't finish project |
| user tries to start a planned project | user can't finish project|
| user does not want to finish project but wants to see what projects are available to be finishedd | page displays a list of available projects to finish with a button click |

PostCondition: project is finished
