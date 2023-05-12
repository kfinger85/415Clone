# ISP: COMPANY

## Company(name: String)


| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Set of Available Workers | size | Empty set of Available workers | [] | *tested elsewhere |
|   |   | 1 Available worker | size = 1 | *tested elsewhere |
|   |   | Multiple Available workers | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Projects | size | Empty set of projects | [] | *tested elsewhere |
|   |   | 1 project | size = 1 | *tested elsewhere |
|   |   | Multiple projects | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *tested elsewhere |
|   |   | 1 Assigned  worker | size = 1 | *tested elsewhere |
|   |   | Multiple Assigned workers | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of employed Workers | size | Empty set of employed | [] | *tested elsewhere |
|   |   | 1 employed | size = 1 | *tested elsewhere |
|   |   | Multiple employed | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Qualifications | size | no qualification | throws Exception | *tested elsewhere |
|   |   | 1 qualification | "svelte" | *tested elsewhere |
|   |   | multiple qualification | "svelte", "ook!", "chicken" | *tested elsewhere | 
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | "svelte" | *tested elsewhere |
| name | Company has a valid name | Yes, valid name | "Company name" | testConstructorSingleLetterAndMultipleWordString() |
|   |   | not valid | "" | testConstructorEmptyString() |
|   | nullness | null | null | testConstructorNull() |
|   |   | not null | "not null" | testConstructorNotNull() |


## BASE CHOICE COVERAGE: Company
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Oracle | 
|---|---|---|---|---|---|---|---|
| T2 | valid name | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS | 
| T3 | valid name | 1 employee | 1 available | 1 assigned | 1 project | **Qualifications = 0** | FAIL | 
| T4 | valid name | 1 employee | 1 available | 1 assigned | **0 project** | 1 or more Qualifications | PASS |
| T5 | valid name | 1 employee | 1 available | **0 assigned** | 1 project | 1 or more Qualifications | PASS | 
| T6 | valid name | 1 employee | **0 available** | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T7 | valid name | **0 employee** | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T8 | **invalid name** | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | FAIL | 


                                            


## equals(o: Object)

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| OBJECT | What kind of Object | Exact same Object | Company c = new Company("Company name"); | testEqualsReflexive() |
|   |   | different Object | Company c = new Company("Snack Truck");<br/><br/> Worker y = new Worker("BoB", qualificationSet, 10);<br/><br/>Project p = new Project("project", qualificationSet, ProjectSize.SMALL) | testEqualsWithDifferentObjects() |
|   |   | Instance of object with different params | Company c = new Company("equalCompany");<br/><br/>Company d = new Company("equalCompany");<br/><br/>Company e = new Company("Not Equal"); | testEqualsConsistent() |
|   |   | Null object | Company c = new Company("Company Name");<br/><br/> null | testEqualsWithNull() |
| Name | nullness | null |   | *tested elsewhere |                 
 |   |   | not null |   | *tested elsewhere |
| set of employees | nullness | null |   | *tested elsewhere |
|   |   | not null |   | *tested elsewhere |
|   | size | size = 0 |   | *tested elsewhere |
|   |   | size >= 1 |   | *tested elsewhere |
| set of available | nullness | null |   | *tested elsewhere |
|   |   | not null |   | *tested elsewhere |
|   | size | size = 0 |   | *tested elsewhere |
|   |   | size >= 1 |   | *tested elsewhere |
| set of assigned | nullness | null |   | *tested elsewhere |
|   |   | not null |   | *tested elsewhere |
|   | size | size = 0 |   | *tested elsewhere |
|   |   | size >= 1 |   | *tested elsewhere |
| set of projects | nullness | null |   | *tested elsewhere |
|   |   | not null |   | *tested elsewhere |
|   | size | size = 0 |   | *tested elsewhere |
|   |   | size >= 1 |   | *tested elsewhere |
| set of qualifications | nullness | null |   | *tested elsewhere |
|   |   | not null |   | *tested elsewhere |
|   | size | size = 0 |   | *tested elsewhere |
|   |   | size >= 1 |   | *tested elsewhere  |

## BASE CHOICE COVERAGE: equals

| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Object | Oracle | 
|---|---|---|---|---|---|---|---|---|
| T1(BASE) | "Bob_1" | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | equal company | **PASS** | 
| T2 | "Bob_1" | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | **not equal** | FAIL | 
| T3 | "Bob_1" | 1 employee | 1 available | 1 assigned | 1 project | **Qualifications = 0** | equal company | PASS | 
| T4 | "Bob_1" | 1 employee | 1 available | 1 assigned | **0 project** | 1 or more Qualifications | equal company | PASS |
| T5 | "Bob_1" | 1 employee | 1 available | **0 assigned** | 1 project | 1 or more Qualifications | equal company | PASS | 
| T6 | "Bob_1" | 1 employee | **0 available** | 1 assigned | 1 project | 1 or more Qualifications | equal company | PASS |
| T7 | "Bob_1" | **0 employee** | 1 available | 1 assigned | 1 project | 1 or more Qualifications | equal company | PASS |
| T8 | **"Bob_2"** | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | equal company | PASS | 


## toString() : String

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| name | validity | is valid | "company name" | testCompanyToStringZeroZero |
|  |  | not valid | constructor prevents this |  |
| Availeble | size | empty | <> | testCompanyToStringZeroZero |
|  |  | Not empty | ("jeff", qualifications, 10000) | testCompanyToStringOneWorker |
| Projects | size | empty | <> | testCompanyToStringZeroZero |
|  |  | Not empty | ("Test", qualifications, ProjectSize.SMALL) | testCompanyToStringOneWorkerOneProject |

## BASE CHOICE COVERAGE: toString()

| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Oracle | 
|---|---|---|---|---|---|---|---|
| T1(BASE) | "company_name" | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS | 
| T2 | "company_name" | 1 employee | 1 available | 1 assigned | 1 project | **Qualifications = 0** | equal company | PASS | 
| T3 | "company_name" | 1 employee | 1 available | 1 assigned | **0 project** | 1 or more Qualifications | PASS |
| T4 | "company_name" | 1 employee | 1 available | **0 assigned** | 1 project | 1 or more Qualifications | PASS | 
| T5 | "company_name" | 1 employee | **0 available** | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T6 | "company_name" | **0 employee** | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T7 | **"company_name2"** | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications |  PASS | 

## getName() : String

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Set of Available Workers | size | Empty set of Available workers | [] | *tested elsewhere |
|    |   | 1 Available worker | size = 1 | *tested elsewhere |
|    |   | Multiple Available workers | size > 1 | *tested elsewhere |
|    | nullness | null | null | *tested elsewhere |
|    |   | not null | not null | *tested elsewhere |
| Set of Projects | size | Empty set of projects | [] | *tested elsewhere |
|    |   | 1 project | size = 1 | *tested elsewhere |
|    |   | Multiple projects | size > 1 | *tested elsewhere |
|    | nullness | null | null | *tested elsewhere |
|    |   | not null | not null | *tested elsewhere |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *tested elsewhere |
|    |   | 1 Assigned  worker | size = 1 | *tested elsewhere |
|    |   | Multiple Assigned workers | size > 1 | *tested elsewhere |
|    | nullness | null | null | *tested elsewhere |
|    |   | not null | not null | *tested elsewhere |
| Set of employed Workers | size | Empty set of employed | [] | *tested elsewhere |
|    |   | 1 employed | size = 1 | *tested elsewhere |
|    |   | Multiple employed | size > 1 | *tested elsewhere |
|    | nullness | null | null | *tested elsewhere |
|    |   | not null | not null | *tested elsewhere |
| Set of Qualifications | size | no qualification | throws Exception | *tested elsewhere |
|    |   | 1 qualification | "svelte" | *tested elsewhere |
|    |   | multiple qualification | "svelte", "ook!", "chicken" | *tested elsewhere | 
|    | nullness | null | null | *tested elsewhere |
|    |   | not null | "svelte" | *tested elsewhere |
| name | Company has a valid name | Yes, valid name | "Company name" | testConstructorSingleLetterAndMultipleWordString() |
|    |   | not valid | "" | testConstructorEmptyString() |
|    | nullness | null | null | testConstructorNull() |
|    |   | not null | "not null" | testConstructorNotNull() |
|    | length | one word | "Company" | testConstructorSingleLetterAndMultipleWordString()   |
|    |   | multiple words | "Company name" | testEqualsTransitive()  |


## BASE CHOICE COVERAGE: getName()
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Oracle | 
|---|---|---|---|---|---|---|---|
| T1(BASE) | valid name | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | **PASS** |
| T2 | valid name | 1 employee | 1 available | 1 assigned | 1 project | **Qualifications = 0** | FAIL | 
| T3 | valid name | 1 employee | 1 available | 1 assigned | **0 project** | 1 or more Qualifications | PASS |
| T4 | valid name | 1 employee | 1 available | **0 assigned** | 1 project | 1 or more Qualifications | PASS | 
| T5 | valid name | 1 employee | **0 available** | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T6 | valid name | **0 employee** | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS |
| T7 | **invalid name** | 1 employee | 1 available | 1 assigned | 1 project | 1 or more Qualifications | PASS | 

## getEmployedWorkers() : Set < Worker >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Set of Employed workers | size | no employed workers | [] |   |
|   |   | 1 employed worker | workerOne = new Worker("Bob_1", qualificationSetOne, 10); | testCompanyCreateWorkerExpected() |
|   |   | multiple employed workers | multiple valid workers |   |
|   | nullness | null | null |   |
|   |   | not null | workerOne = new Worker("Bob_1", qualificationSetOne, 10); |   |

## getAvailableWorkers() : Set < Worker >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Set of Available workers | size | no available workers | [] |   |
|   |   | 1 available worker | workerOne = new Worker("Bob_1", qualificationSetOne, 10); | testCompanyCreateWorkerExpected() |
|   |   | multiple available workers | multiple valid workers | testAssignIsNotHelpful() |
|   | nullness | null | null |   |
|   |   | not null | workerOne = new Worker("Bob_1", qualificationSetOne, 10); |   |

## getUnavailableWorkers() : Set < Worker >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Worker | nullness | null worker | null<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | *already tested |
|   |   | not null worker | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | *already tested |
|   | is worker available? | yes worker is | "Bob_1" is available | testgetUnavailable() |
|   | Worker meets required qualifications | yes worker does | "Bob_1" meets qualifications | testgetUnavailable() |
|   |   | no worker doesn't | "Bob_1" does not meet required qualifications | testgetUnavailable() |
|   | is worker already in project | yes worker is | "Bob_1" is in project | testgetUnavailable() | 
| Project | nullness | null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>null | *already tested |
|   |   | not null project | project = new project"..correct things..." | *already tested |
|   | projects qualifications are met | yes they are | project_1 qualifications are met | testgetUnavailable(){ |
|   |   | no they aren't | project_1 qualifications are not met | testgetUnavailable() |
|   |   | not null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | *already tested |
| Set of Available Workers | size | Empty set of Available workers | [] | *already tested |
|   |   | 1 Available worker | size = 1 | *already tested |
|   |   | Multiple Available workers | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Projects | size | Empty set of projects | [] | *already tested |
|   |   | 1 project | size = 1 | *already tested |
|   |   | Multiple projects | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *already tested |
|   |   | 1 Assigned  worker | size = 1 | *already tested |
|   |   | Multiple Assigned workers | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of employed Workers | size | Empty set of employed | [] | *already tested |
|   |   | 1 employed | size = 1 | *already tested |
|   |   | Multiple employed | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Qualifications | size | no qualification | throws Exception | *already tested |
|   |   | 1 qualification | "svelte" | *already tested |
|   |   | multiple qualification | "svelte", "ook!", "chicken" | *already tested |
|   | qualifications are a subset of Worker | yes they are | Q's are subset of Worker | testgetUnavailable() |
|   |   | no they aren't | Q's are not subset of Worker | testgetUnavailable() |
|   | qualifications are a subset of Project | yes they are | Q's are a subset of Project | testgetUnavailable() |
|   |   | no they aren't | Q's are not a subject of Project | testgetUnavailable() |
|   | nullness | null | null | *already tested |
|   |   | not null | "svelte" | *already tested |
| name | worker has a valid name | Yes, valid name | "Bob_1" | *already tested |
|   |   | not valid | "" | *already tested |

## BASE CHOICE COVERAGE: getUnavailableWorkers()
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Worker | Project | Oracle |
|---|---|---|---|---|---|---|---|---|---|
| T1(BASE) | "Bob_1" | yes is an employee | not in available | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | 1 unavailable worker |
| T2 | "Bob_1" | yes is an employee | not in available | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | worker is at max load | **project is FINISHED** | 1 unavailable worker |
| T3 | "Bob_1" | yes is an employee | not in available | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | **worker is NOT at max load** | project status is ACTIVE | 1 available worker |
| T4 | "Bob_1" | yes is an employee | not in available | is assigned to Project | multiple projects | **Qualifications are NOT a subset of worker and project** | worker is at max load | project status is ACTIVE | 1 unavailable worker |
| T5 | "Bob_1" | yes is an employee | not in available | is assigned to Project | **1 project** | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | Fail (row can't work) |
| T6 | "Bob_1" | yes is an employee | not in available | **is not assigned to Project** | multiple projects | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | Fail (row can't work) |
| T7 | "Bob_1" | yes is an employee | **in available** | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | Fail (row can't work) |
| T8 | "Bob_1" | **not an employee** | not in available | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | Fail (row won't happen) |
| T9 | **""** | yes is an employee | not in available | is assigned to Project | multiple projects | Qualifications are a subset of worker and project | worker is at max load | project status is ACTIVE | Fail |


## getAssignedWorkers() : Set < Worker >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Set of Available Workers | size | Empty set of Available workers | [] | *tested elsewhere |
|   |   | 1 Available worker | size = 1 | *tested elsewhere |
|   |   | Multiple Available workers | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Projects | size | Empty set of projects | [] | *tested elsewhere |
|   |   | 1 project | size = 1 | *tested elsewhere |
|   |   | Multiple projects | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | testAssignIsNotHelpful() |
|   |   | 1 Assigned  worker | size = 1 | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | Multiple Assigned workers | size > 1 | testAssignIsNotHelpful() |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of employed Workers | size | Empty set of employed | [] | *tested elsewhere |
|   |   | 1 employed | size = 1 | *tested elsewhere |
|   |   | Multiple employed | size > 1 | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | not null | *tested elsewhere |
| Set of Qualifications | size | no qualification | throws Exception | *tested elsewhere |
|   |   | 1 qualification | "svelte" | *tested elsewhere |
|   |   | multiple qualification | "svelte", "ook!", "chicken" | *tested elsewhere | 
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | "svelte" | *tested elsewhere |
| name | Company has a valid name | Yes, valid name | "Company name" | *tested elsewhere |
|   |   | not valid | "" | *tested elsewhere |
|   | nullness | null | null | *tested elsewhere |
|   |   | not null | "not null" | *tested elsewhere |   

## Base Choice getAssignedWorkers() 
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Oracle |
|---|---|---|---|---|---|---|---|
| T1(Base) | valid name | yes is an employee | in available | 1 assigned worker | 1 projects | 1 qualification | PASS |
| T2 | valid name | yes is an employee | in available | 1 assigned worker | 1 projects | **multiple qualifications** | PASS |
| T3 | valid name | yes is an employee | in available | 1 assigned worker | **multiple project** | 1 qualification | PASS |
| T4 | valid name | yes is an employee | in available | **No assigned worker** | 1 projects | multiple qualifications | FAIL |
| T5 | valid name | yes is an employee | **NOT in available** | 1 assigned worker | 1 projects | 1 qualification | FAIL |
| T6 | valid name | **not an employee** | in available | 1 assigned worker | 1 projects | multiple qualifications | FAIL |
| T7 | **invalid name** | yes is an employee | in available | 1 assigned worker | 1 projects | 1 qualification | FAIL |

## getUnassignedWorkers() : Set < Worker >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |


## getProjects() : Set < Project >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Worker | nullness | null worker | null<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | *already tested |
|   |   | not null worker | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | *already tested |
| Project | nullness | null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>null | *already tested |
|   |   | not null project | project = new project"..correct things..." | *already tested |          
| Set of Available Workers | size | Empty set of Available workers | [] | *already tested |
|   |   | 1 Available worker | size = 1 | *already tested |
|   |   | Multiple Available workers | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Projects | size | Empty set of projects | [] | testNoProjectsCreated() |
|   |   | 1 project | size = 1 | testOneProjectCreated() |
|   |   | Multiple projects | size > 1 | testMultipleProjectsCreated() |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *already tested |
|   |   | 1 Assigned  worker | size = 1 | *already tested |
|   |   | Multiple Assigned workers | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of employed Workers | size | Empty set of employed | [] | *already tested |
|   |   | 1 employed | size = 1 | *already tested |
|   |   | Multiple employed | size > 1 | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | not null | *already tested |
| Set of Qualifications | size | no qualification | throws Exception | *already tested |
|   |   | 1 qualification | "svelte" | *already tested |
|   |   | no they aren't | Q's are not a subject of Project | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | "svelte" | *already tested |
| name | worker has a valid name | Yes, valid name | "Bob_1" | *already tested |
|   |   | not valid | "" / null / all blank space | *already tested |


## BASE CHOICE COVERAGE: getProjects() 
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Worker | Project | Oracle |
|---|---|---|---|---|---|---|---|---|---|
| T1(BASE) | "Bob_1" | yes is an employee | in available | is assigned to Project | 1 projects | 1 or more Qualifications | valid worker | valid project | PASS |
| T2 | "Bob_1" | yes is an employee | in available | is assigned to Project | 1 projects | 1 or more Qualifications | valid worker | **null project** | FAIL |
| T3 | "Bob_1" | yes is an employee | in available | is assigned to Project | 1 projects | 1 or more Qualifications | **null worker** | valid project | FAIL |
| T4 | "Bob_1" | yes is an employee | in available | is assigned to Project | 1 projects | **Qualifications = 0** | valid worker | valid project | FAIL |
| T5 | "Bob_1" | yes is an employee | in available | is assigned to Project | **0 project** | 1 or more Qualifications | valid worker | valid project | Fail |
| T6 | "Bob_1" | yes is an employee | in available | **is not assigned to Project** | 1 projects | 1 or more Qualifications | valid worker | valid project | PASS |
| T7 | "Bob_1" | yes is an employee | **not in available** | is assigned to Project | 1 projects | 1 or more Qualifications | valid worker | valid project | PASS |
| T8 | "Bob_1" | **not an employee** | in available | is assigned to Project | 1 projects | 1 or more Qualifications | valid worker | valid project | PASS |
| T9 | **""** | yes is an employee | in available | is assigned to Project | 1 projects | 1 or more Qualifications | valid worker | valid project | FAIL |

## getQualifications() : Set < Qualification >

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| qualifications| set of qualifications in company object | No qualifications <br/><br/> some Qualifications | qualifications = new HashSet<Qualification>() <br/><br/> qualifications = {qualificamtion1, qualification2} | testGetEmptyQualification <br/><br/> testCompanyCreateWorkerExpected|

## createWorker(name: String, qs: Set< Qualification >, salary: double): Worker

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Name | Nullness | Null | Null | testCompanyCreateWorkerNullCases |
|  |  | NotNull | Bob | testCompanyCreateWorkerExpected |
|  | Emptyness | Empty | "" | testCompanyCreateWorkerEmptyName |
| Qualification Set | Correct Qualifications for company | incorrect | company quals: empty, worker quals <"svelte"> | testCompanyCreateWorkerMissingQualification |
|  |  | correct | company quals: <"1stQualification">, worker quals <"1stQualification"> | testCompanyCreateWorkerExpected |
| Salary | negative or positive | negative | -100 | testCompanyCreateWorkerNegativeSalary |
|  |  | positive | 10 | testCompanyCreateWorkerExpected |
  
## Base Choice createWorker() 
| Test | Name | Qualification Set | Salary | Set of Employees | Available Set | Assigned Set | Projects Set | Oracle |
|---|---|---|---|---|---|---|---|---|
| T1(Base) | valid name | correct qualifications | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | 1 project | PASS |
| T2 | valid name | correct qualifications | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | **multiple projects** | PASS |
| T3 | valid name | correct qualifications | non negative salary | Not already in set of Employees| 1 Available |**multiple assigned** | 1 project | PASS |
| T4 | valid name | correct qualifications | non negative salary | Not already in set of Employees| **multiple Available** | 1 assigned | 1 project | PASS |
| T5 | valid name | correct qualifications | non negative salary |  **already in set of Employees** | 1 Available | 1 assigned | 1 project | FAIL |
| T6 | valid name | correct qualifications | **negative salary** | Not already in set of Employees| 1 Available | 1 assigned | 1 project | FAIL |
| T7 | valid name | **qualification company doesnt have** | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | 1 project | FAIL |
| T8 | **invalid name** | correct qualifications | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | 1 project | FAIL |

## createQualification(description: String): Qualification

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| description | string | empty <br/><br/> null <br/><br/> valid string | "" <br/><br/> null <br/><br/> "svelte" | testCreateQualificationEmpty <br/><br/> testCreateQualificationNull <br/><br/> testCreateQualification |
  
## Base Choice createQualification() 
| Test | Description | Qualification Set | Employees Set | Available Set | Assigned Set | Projects Set | Oracle |
|---|---|---|---|---|---|---|---|
| T1(Base) | valid description | not already in qualification set | 1 employee | 1 Availeble | 1 assigned | 1 project | PASS |
| T3 | valid description | not already in qualification set | 1 employee |1 Availeble | 1 assigned | **multiple  projects** | PASS |
| T3 | valid description | not already in qualification set | 1 employee |1 Availeble | **none assigned** | 1  projects | PASS |
| T4 | valid description | not already in qualification set | 1 employee | **none  availeble** | 1 assigned | 1  projects | PASS |
| T5 | valid description | not already in qualification set | **multiple employees** | 1 Availeble | 1 assigned | 1 project | PASS |
| T6 | valid description | **already in qualification set** | 1 employee | 1 Availeble | 1 assigned | 1 project | FAIL |
| T7 | **invalid description** | not already in qualification set | 1 employee | 1 Availeble | 1 assigned | 1 project | FAIL |

## createProject(name: String, qs: Set(Qualification), size: ProjectSize): Project

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Name | Nullness | Null | Null | testCompanyCreateProjectNullName |
|  |  | NotNull | test | testCompanyCreateProjectNormal |
|  | Emptyness | Empty | "" | testCompanyCreateProjectEmptyName |
| Qualification Set | Nullness | Null | null | testCompanyCreateProjectNullQual |
|  |  | Not null | svelte | testCompanyCreateProjectNormal |
|  | Emptyness | Empty | <> | testCompanyCreateProjectEmptyQual |
| Size | Nullness | Null | null | testCompanyCreateProjectNullQual |
|  |  | Not null | svelte | testCompanyCreateProjectNormal |
|  | Size | small | ProjectSize.SMALL | testCompanyCreateProjectEmptyQual |
|  |  | medium | ProjectSize.MEDIUM | testStart |
|  |  | large | ProjectSize.BIG | testWorkerBecomesAvailableAfterUnassign |
  
## Base Choice createProject() 
| Test | Name | Qualification Set | size | Set of Employees | Available Set | Assigned Set | Projects Set | Oracle |
|---|---|---|---|---|---|---|---|---|
| T1(Base) | valid name | correct qualifications | valid size | Not already in set of Employees| 1 Available | 1 assigned | not already in projects | PASS |
| T2 | valid name | correct qualifications | valid size | Not already in set of Employees| 1 Available | 1 assigned | **already in projects** | FAIL |
| T3 | valid name | correct qualifications | valid size | Not already in set of Employees| 1 Available |**multiple assigned** | not already in projects | PASS |
| T4 | valid name | correct qualifications | valid size | Not already in set of Employees| **multiple Available** | 1 assigned | not already in projects | PASS |
| T5 | valid name | correct qualifications | valid size |  **already in set of Employees** | 1 Available | 1 assigned | not already in projects| FAIL |
| T6 | valid name | correct qualifications | **invalid size** | Not already in set of Employees| 1 Available | 1 assigned | not already in projects | FAIL |
| T7 | valid name | **qualification company doesnt have** | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | not already in projects | FAIL |
| T8 | **invalid name** | correct qualifications | non negative salary | Not already in set of Employees| 1 Available | 1 assigned | not already in projects | FAIL |

## start(p : Project): void

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Project | Companys projects | company has project | ("project1", qualificationSetFour, ProjectSize.MEDIUM) | testStart |
|  | | company doesnt have project | ("notOurJob", qualificationSetOne, ProjectSize.BIG) | testStartNotOurJob |
|  | Status | cant be started | active | testStartWrongStatusActive |
|  |  |  | finished | testStartWrongStatusFinished |
|  | can be started | planned | testStart |
| Workers | Qualifications | Workers have correct qualifications | qualificationSetFour | testStart |
|  |  | Workers dont correct qualifications | qualificationSetTwo | testStartNotFulfilled |
  
## Base Choice start() 
| Test | Company's Project(s) | Project Status | Workers Qualifications | Set of employees | Assigned Set | Qualifications Set | Available set | Oracle |
|---|---|---|---|---|---|---|---|---|
| T1(Base) | company has project | project != active or finished | qualifications meet project | employees are in company and project | 1 assigned to project | company covers qualifications | 1 available |PASS |
| T2 | company has project | project != active or finished | qualifications meet project | employees are in company and project | 1 assigned to project | company covers qualifications | none available | Pass |
| T2 | company has project | project != active or finished | qualifications meet project | employees are in company and project | 1 assigned to project | **company missing qualifications** | 1 available |FAIL |
| T3 | company has project | project != active or finished | qualifications meet project | employees are in company and project | **0 assigned to project** | company covers qualifications |1 available | PASS |
| T4 | company has project | project != active or finished | qualifications meet project | **employees arent in company** | 1 assigned to project | company covers qualifications |1 available |FAIL |
| T5 | company has project | project != active or finished | **worker(s) have missing qualifications** | employees are in company and project | 1 assigned to project | company covers qualifications |1 available |FAIL |
| T6 | company has project | **project = active or finished** | qualifications meet project |employees are in company and project | 1 assigned to project | company covers qualifications | 1 available |FAIL |
| T7 | **company doesnt have project** | project != active or finished | qualifications meet project |employees are in company and project | 1 assigned to project | company covers qualifications | 1 available |FAIL |
  
  
## finish(p : Project): void

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Project | Companys projects | company has project | ("project1", qualificationSetFour, ProjectSize.MEDIUM) | testFinish |
|  | | company doesnt have project | ("not our job", qualificationSetFour, ProjectSize.MEDIUM) | testFinishedWrongStatusNotOurJob |
|  | Status | cant be finished | suspended | testFinishedWrongStatusSuspended |
|  |  |  | finished | testFinishedWrongStatusFinished |
|  |  |  | PLANNED | testFinishedWrongStatusPlanned |
| | | can be finished | active | testFinish |
  
## Base Choice Finish() 
| Test | Company's Project(s) | Project Status | Set of employees | Assigned Set | Qualifications Set | Available set | Oracle |
|---|---|---|---|---|---|---|---|
| T1(Base) | company has project | project != suspended or finished or planned | 1 employee | 1 assigned | 1 qualification | 1 available | PASS |
| T2 | company has project | project != suspended or finished or planned | 1 employee | 1 assigned | 1 qualification | **multiple available** | PASS |
| T2 | company has project | project != suspended or finished or planned | 1 employee | 1 assigned | **multiple qualifications** | 1 available | PASS |
| T2 | company has project | project != suspended or finished or planned | **multiple employees**| 1 assigned | 1 qualification | 1 available | PASS |
| T2 | company has project | **project = suspended or finished or planned** | 1 employee | 1 assigned | 1 qualification | 1 available | FAIL |
| T3 | **company doesnt have project** | project != suspended or finished or planned |  1 employee | 1 assigned | 1 qualification | 1 available |FAIL |

## assign(w : Worker, p: Project): void

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Worker | nullness | null worker | null<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignWorkerisNull() |
|   |   | not null worker | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   | is worker available? | yes worker is | "Bob_1" is availanle | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | no worker isn't | "Bob_1" is not avaialable | testAssignWorkerIsNotInAvailable()  |
|   | is worker already in project | yes worker is | "Bob_1" is in project | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications()|
|   |   | no worker isn't | "Bob_1" is not in project | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
|   | will worker be overloaded if added to project | will overload | "Bob_1" will be overloaded from added project | testAssignWorkerWillOverloadProject() |
|   |   | will not overload | "Bob_1" will not be overloaded from added project | testAssignWorkerWillOverloadProject() |
|   | is worker assigned? | yes worker is | "Bob_1" is assigned already | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | no worker isn't | "Bob_1" is not assigned already | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned |
|   | is worker helpful? | yes worker is | "Bob_1" is helpful to project | testAssignIsHelpful() |
|   |   | no worker isn't | "Bob_1" is not helpful to project | testAssignIsNotHelpful() |
| Project | nullness | null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>null | testAssignProjectisNull() |
|   |   | not null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   | is project status Active? | Active | ProjectStatus.ACTIVE | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   |   | not Active | Not ACTIVE | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   | is project status Finished? | Finished | ProjectStatus.FINISHED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   |   | Not Finished | Not FINISHED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   | is project status Planned? | Planned | ProjectStatus.PLANNED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   |   | Not Planned | Not PLANNED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   | is project status Suspended | Suspended | ProjectStatus.SUSPENDED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
|   |   | Not Suspended | Not SUSPENDED | testAssignCorrectlyDoesNotAssignWorkerBecaseActiveAndFinished |
| Set of Available Workers | size | Empty set of Available workers | [] | *already tested |
|   |   | 1 Available worker | size = 1 | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | Multiple Available workers | size > 1 | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
| Set of Projects | size | Empty set of projects | [] | *already tested |
|   |   | 1 project | size = 1 | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | Multiple projects | size > 1 | testAssignWorkerWillOverloadProject() |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *already tested |
|   |   | 1 Assigned  worker | size = 1 | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | Multiple Assigned workers | size > 1 | testAssignIsNotHelpful()  |
| Set of employed Workers | size | Empty set of employed | [] | *already tested |
|   |   | 1 employed | size = 1 | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | Multiple employed | size > 1 | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
| Set of Qualifications | size | no qualification | throws Exception | *already tested |
|   |   | 1 qualification | "svelte" | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   |   | multiple qualification | "svelte", "ook!", "chicken" | testAssignIsHelpful() |
|   | qualifications are a subset of Worker | yes they are | Q's are subset of Worker | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
|   |   | no they aren't | Q's are not subset of Worker | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
|   | qualifications are a subset of Project | yes they are | Q's are a subset of Project | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
|   |   | no they aren't | Q's are not a subject of Project | testAssignCorrectlyNotAssigningIfWorkerDoesNotHaveAnyMissingQualifications() |
|   | nullness | null | null | *already tested |
|   |   | not null | "svelte" | *already tested |
| name | worker has a valid name | Yes, valid name | "Bob_1" | *already tested |
|   |   | not valid | "" | *already tested |

## BASE CHOICE COVERAGE: assign 
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Worker | Project | Oracle |
|---|---|---|---|---|---|---|---|---|---|
| T1(BASE) | "Bob_1" | yes is an employee | yes is in available | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Pass |
| T2 | "Bob_1" | yes is an employee | yes is in available | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | **project is FINISHED** | Fail |
| T3 | "Bob_1" | yes is an employee | yes is in available | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | **worker will be overloaded** | project status is PLANNED | Fail |
| T4 | "Bob_1" | yes is an employee | yes is in available | is not assigned to Project | 1 project | **Qualifations are NOT a subset of worker and project** | worker will not be overloaded | project status is PLANNED | Fail |
| T5 | "Bob_1" | yes is an employee | yes is in available | is not assigned to Project | **0 project** | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T6 | "Bob_1" | yes is an employee | yes is in available | **is assigned to Project** | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T7 | "Bob_1" | yes is an employee | **not available** | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T8 | "Bob_1" | **not an employee** | yes is in available | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T9 | **""** | yes is an employee | yes is in available | is not assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNNED | Fail |

## unassign(w: Worker, p: Project): void

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Worker | nullness | null worker | null<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignWorkerisNull() |
|   |   | not null worker | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   | Is worker assigned? | yes worker is |   |   |
|   |   | no worker isn't |   |   |
|   | Amount of projects worker has | no projects |   |   |
|   |   | one project |   |   |
|   |   | multiple projects |   |   |
|   | Does worker become available after removing a project ? | yes worker does | load < 12 |   |
|   |   | no worker doesnt | load == 12 |   |
| Project | nullness | null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>null | testAssignProjectisNull() |
|   |   | not null project | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testAssignCorrectlyAssignsWorkerFromAvailableToAssigned() |
|   | Are project qualifications still satisfied? | yes they are |   |   |
|   |   | no they aren't |   |   |

## BASE CHOICE COVERAGE: unassign
| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Worker | Project | Oracle |
|---|---|---|---|---|---|---|---|---|---|
| T1(BASE) | "Bob_1" | yes is an employee | yes is in available | is assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Pass |
| T2 | "Bob_1" | yes is an employee | yes is in available | is assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | **project is FINISHED** | Pass |
| T3 | "Bob_1" | yes is an employee | yes is in available | is assigned to Project | 1 project | Qualifations are a subset of worker and project | **worker will be overloaded** | project status is PLANNED | Fail |
| T4 | "Bob_1" | yes is an employee | yes is in available | is assigned to Project | 1 project | **Qualifations are NOT a subset of worker and project** | worker will not be overloaded | project status is PLANNED | Fail |
| T5 | "Bob_1" | yes is an employee | yes is in available | is assigned to Project | **0 project** | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T6 | "Bob_1" | yes is an employee | yes is in available | **is not assigned to Project** | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T7 | "Bob_1" | yes is an employee | **not available** | is assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Pass |
| T8 | "Bob_1" | **not an employee** | yes is in available | is assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNED | Fail |
| T9 | **""** | yes is an employee | yes is in available | is assigned to Project | 1 project | Qualifations are a subset of worker and project | worker will not be overloaded | project status is PLANNNED | Fail |


## unassignAll(w: Worker): void

| Variable | Characteristic | Partition | Value | Function |
| -------- | -------------- | --------- | ----- | -------- |
| Worker | nullness | null worker | null<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testUnassignAllNullWorker() |
|   |   | not null worker | worker = new Worker("Bob", qualificationSet, 10.30);<br/>project = new Project("project name", qualificationSet, ProjectSize.SMALL); | testUnassignALLCorrectlyUnassignsMultiple() |
|   | is worker available? | yes worker is | "Bob_1" is available | *tested elsewhere |
|   |   | no worker isn't | "Bob_1" is not avaialable | *tested elsewhere |
|   | how many projects is worker in? | 1 or more projects | { project 1, project 2 } | testUnassignALLCorrectlyUnassignsMultiple() |
|   |   | 0 projects | { } | testUnassignALLCorrectlyUnassignsOne() |
|   | is worker assigned? | yes worker is | "Bob_1" is assigned already | testUnassignALLCorrectlyUnassignsOne() |
|   |   | no worker isn't | "Bob_1" is not assigned already | *already tested |
|   | What company is worker in? | this company | worker -> this company | testUnassignALLCorrectlyUnassignsMultiple() |
|   |   | other company | worker ->other company | testUnassignALLNotinCompany() |
| Set of Available Workers | size | Empty set of Available workers | [] | *already tested |
|   |   | 1 Available worker | size = 1 | *tested elsewhere |
|   |   | Multiple Available workers | size > 1 | *tested elsewhere |
| Set of Projects | size | Empty set of projects | [] | *already tested |
|   |   | 1 project | size = 1 | testUnassignALLCorrectlyUnassignsOne() |
|   |   | Multiple projects | size > 1 | testUnassignALLCorrectlyUnassignsMultiple() |
| Set of Assigned Workers | size | Empty set of Assigned workers | [] | *already tested |
|   |   | 1 Assigned  worker | size = 1 | testUnassignALLCorrectlyUnassignsOne() |
|   |   | Multiple Assigned workers | size > 1 | testUnassignALLCorrectlyUnassignsMultiple() |
| Set of employed Workers | size | Empty set of employed | [] | *already tested |
|   |   | 1 employed | size = 1 | testUnassignALLCorrectlyUnassignsOne() |
|   |   | Multiple employed | size > 1 | testUnassignALLCorrectlyUnassignsMultiple() |
| Set of Qualifications | size | no qualification | throws Exception | *already tested |
|   |   | 1 qualification | "svelte" | *already tested |
|   |   | multiple qualification | "svelte", "ook!", "chicken" | *already tested |
|   | qualifications are a subset of Worker | yes they are | Q's are subset of Worker | testUnassignALLCorrectlyUnassignsMultiple() |
|   |   | no they aren't | Q's are not subset of Worker | *already tested |
|   | qualifications are a subset of Project | yes they are | Q's are a subset of Project | testUnassignALLCorrectlyUnassignsMultiple() |
|   |   | no they aren't | Q's are not a subject of Project | *already tested |
|   | nullness | null | null | *already tested |
|   |   | not null | "svelte" | *already tested |
| name | company has a valid name | Yes, valid name | "Company name" | *already tested |
|   |   | not valid | "" | *already tested |


## BASE CHOICE COVERAGE: unassignAll

| Test | Name | Employee set | Available set | Assigned set | Projects set | Qualifications set | Worker | Oracle |
|---|---|---|---|---|---|---|---|---|
| T1 | valid name | yes is an employee | worker is in available | worker is assigned to Project | multiple projects in company | Qualifications are a subset of worker and project | worker is in company | PASS |
| T2 | valid name | yes is an employee | worker is in available | worker is assigned to Project | multiple projects in company | Qualifications are a subset of worker and project | **worker in another company** | FAIL |
| T3 | valid name | yes is an employee | worker is in available | worker is assigned to Project | multiple projects in company | **Qualifications are NOT a subset of worker and project** | worker is in company | FAIL |
| T4 | valid name | yes is an employee | worker is in available | worker is assigned to Project | **0 projects in company** | Qualifications are a subset of worker and project | worker is in company | PASS |
| T5 | valid name | yes is an employee | worker is in available | **worker is not assigned to Project** | multiple projects in company | Qualifications are a subset of worker and project | worker is in company | PASS |
| T6 | valid name | yes is an employee | **not in available** | worker is assigned to Project | multiple projects in company | Qualifications are a subset of worker and project | worker is in company | PASS |
| T7 | valid name | **not an employee** | worker is in available | worker is assigned to Project | multiple projects in company | Qualifications are a subset of worker and project | worker is in company | FAIL |
| T8 | invalid name | yes is an employee | worker is in available | worker is assigned to Project | multiple projects in company | Qualifications are a subset of worker and project | worker is in company | FAIL |

