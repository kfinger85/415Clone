
<!Doctype html>
<html>

<body>
    <table>
        <tr>
            <th colspan="5">ISP: Qualification</th>
        </tr>
        <tr>
            <th>Variable</th>
            <th>Characteristic</th>
            <th>Partition</th>
            <th>Value</th>
            <th>Function</th>
        </tr>
        <td rowspan="5">Set of Workers</td>
        <td rowspan="3">A) number of Workers</td>
        <td>A1) no workers</td>
        <td>an empty set of workers</td>
        <td>testGetWorkersReturnEmptySet</td>
        <tr>
            <td>A2) one worker </td>
            <td>Worker A = new Worker("Bob", qualificationSet, 10);</td>
            <td>testAddWorkersAndGetWorkerAddingOneWorker<br><br>
                testRemoveWorkerRemovingOne<br><br>
                testDTOMatchWithSingleWorkers
            </td>
        </tr>
        <tr>
            <td>A3) multiple workers</td>
            <td>Worker A = new Worker("Bob_1", qualificationSet, 10);<br><br>
		        Worker B = new Worker("Bob_2", qualificationSet, 10);<br><br>
		        Worker C = new Worker("Bob_3", qualificationSet, 10);<br><br>
		        Worker D = new Worker("Bob_4", qualificationSet, 10);<br><br>
		        Worker E = new Worker("Bob_5", qualificationSet, 10);<br><br>
                null worker
            </td>
            <td>testRemoveWorkerSuccessfullyRemovingMultiple<br><br>
                testAddWorkerWithMultipleAddition<br><br>
                testAddWorkerWithMultipleWorkersAndNull<br><br>
                testDTOMatchMultipleWorkers
            </td>
        </tr>
        <tr>
            <td rowspan="2">B) Qualifications</td>
            <td>B1) new Qualification</td>
            <td>"python"<br><br>
                "React"
            </td>
            <td>testHashCodeWithDifferentQualfications</td>
        </tr>
        <tr>
            <td>B2) duplicate Qualification</td>
            <td>"python"<br><br>
                "python"
            </td>
            <td>testNotAddingDuplicateQualificationsToSet</td>
        </tr>
        <tr>
            <td rowspan="5">Description</td>
            <td rowspan="3">C) length of description</td>
            <td>C1) description is empty </td>
            <td>""</td>
            <td>testToStringEmptyString</td>
        </tr>
        <tr>
            <td>C2) description is one word.</td>
            <td>"one"</td>
            <td>testToStringSingleWord</td>
        </tr>
        <tr>
            <td>C3) description is multiple words</td>
            <td>"This is my qualification"</td>
            <td>testToStringWithMultipleWords</td>
        </tr>
        <tr>
            <td rowspan="2">D) null/ not null</td>
            <td>D1) description is null</td>
            <td> null</td>
            <td>testEqualsWithNullAndOneCorrectQualification (throws exception)</td>
        <tr>
            <td>D2) description is not null</td>
            <td>"not null"</td>
            <td>testObjectNotNull</td>
        </tr>
        </tr>
        <tr>
            <td rowspan="4">Object</td>
            <td rowspan="4">E) What kind of object</td>
            <td>E1) exact same Object</td>
            <td>Qualification q = new Qualification("I am the exact same");</td>
            <td>testEqualsWithExactSameQualificationObject</td>
        </tr>
        <tr>
            <td>E2) different object</td>
            <td>Worker z = new Worker("BoB", qualificationSet, 10);<br><br>
            Qualification q = new Qualification("python");
            </td>
            <td>testEqualsWithDifferentClassEquality</td>
        </tr>
        <tr>
            <td>E3) instance of object with different params</td>
            <td>Qualification q = new Qualification("python"); <br><br>
		        Qualification y = new Qualification("1234567");
            </td>
            <td>testEqualsWithDifferentQualificationsButSameClass</td>
        </tr>
        <tr>
            <td>E4) null object</td>
            <td>null</td>
            <td>testEqualsWithNull (throws exception)</td>
        </tr>
        <tr>
            <td colspan="3"></td>
            <td></td>
            <td></td>
        </tr>   
    </table>
</body>

</html>





<!Doctype html>

<html>
    <body>
    <table>
        <tr>
            <th colspan="5">Input Space Partitioning: Worker</th>
        </tr>
        <tr>
            <th>Variable</th>
            <th>Characteristic</th>
            <th>Partition</th>
            <th>Value</th>
            <th>Function</th>
        </tr>
            <td rowspan="5">Project</td>
            <td rowspan="3">A) Size of project
            </td>
            <td>A1) Small</td>
            <td>"[Project("LP", qualifications, ProjectSize.SMALL)]"</td>
            <td>TestWorkerIsAvailable  </td>
            <tr>
                <td>A2) Medium </td>
                <td>"[Project("LP", qualifications, ProjectSize.MEDIUM)]"</td>
                <td>TestWorkerIsAvailable  </td>
            </tr>
            <tr>
                <td>A3) Large </td>
                <td>"[Project("Album", qualifications, ProjectSize.BIG)]"</td>
                <td>TestWorkerIsAvailable  </td>
            </tr>
            <td rowspan="2">B) Number of Projects
            </td>
            <td>B1) Empty</td>
                <td>"[]"</td>
                 <td>TestWorkerAddAndGetQualification </td>
            <tr>
                <td>B2) Multiple</td>
                <td>"[Project("Album", qualifications, ProjectSize.BIG), Project("LP", qualifications, ProjectSize.SMALL)]"</td>
                <td>TestWorkerAddAndGetQualification </td>
            </tr>     
    <tr>
        <td rowspan="6">Name</td>
            <td rowspan="3">C) null/notnull 
        </td>
        <tr>
            <td>C1)null </td>
                <td>null</td>
                <td>TestWorkerNullName </td>
        </tr>
        <tr>
            <td>C2)not null</td>
                <td>"Gerard Way"</td>
                <td>TestConstructorAddAndGetProject</td>
        </tr>
        <td rowspan="3">D)length
        </td>
            <tr>
                <td>D1)empty </td>
                    <td>""</td>
                     <td>TestConstructorEmptyName </td>
            </tr>
            <tr>
                <td>D2)long </td>
                    <td>"Victor Vincent Fuentes the 13th"</td>
                    <td>TestWorkerGetWorkload  </td>
            </tr>
    </tr>
    <tr>
        <td rowspan="6">qualifications</td>
            <td rowspan="3">E) null/notnull</td>
        <tr>
        <td>E1)null </td>
            <td>null</td>
            <td>TestConstructorNullQualification</td>
        </tr>
        <tr>
        <td>E2)not null </td>
            <td>[description:"not null"]</td>
            <td>TestWorkerConstructor </td>
        </tr>
        <tr> 
            <td rowspan="3">F) length</td>
        </tr>
        <tr>
        <td>F1)empty </td>
            <td>[]</td>
            <td>TestWorkerToString  </td>
        </tr>
        <tr>
        <td>F2)long </td>
            <td>[["long"],["qualified"]]</td>
            <td>TestWorkerEquals</td>
        </tr>
    </tr>
    <tr>
        <td rowspan="6">Salary</td>
            <td rowspan="3">G)Null/Not Null</td>
        <tr>
        <td>G1)Null </td>
            <td>null</td>
            <td>double can never be null(primitive)</td>
        </tr>
        <tr>
        <td>G2)Not null </td>
            <td>10000</td>
            <td>setAndGetWorkerSalary</td>
        </tr>
        <tr> 
            <td rowspan="3">H) negative/positive</td>
        <tr> 
        <td>H1) negative </td>
            <td>-1</td>
            <td>TestConstructorNegativeSalary</td>
        </tr>
        <tr>
        <td>H2) positive</td>
            <td>200000</td>
            <td>TestWorkerToDTO </td>
        </tr>
 </tr>
    </table>
    </body>

</html>



<!Doctype html>

<html>
    <body>
    <table>
        <tr>
            <th colspan="4">Input Space Partitioning: Project</th>
        </tr>
        <tr>
            <th>Variable</th>
            <th>Characteristic</th>
            <th>Partition</th>
            <th>Value</th>
        </tr>
            <td rowspan="5">Workers</td>
            <td rowspan="3">A) Amount of workers in set
            </td>
            <td>A1) Empty</td>
            <td>"[]"</td>
            <tr>
                <td>A2) One worker </td>
                <td>"[["Jill", qualifications, 1]]"</td>
            </tr>
            <tr>
                <td>A3) Many Workers</td>
                <td>"[["Victor Vincent Fuentes the 13th",qualifications,100000"],[Jill", qualifications, 1",etc]]"</td>
            </tr>
            <td rowspan="2">B) Qualifications of workers
            </td>
            <td>B1) New qualification in project</td>
                <td>"["name"]"</td>
            <tr>
                <td>B2) qualifications already in project</td>
                <td>"["name"]"</td>
            </tr>     
    <tr>
        <td rowspan="6">Name</td>
            <td rowspan="3">C) null/notnull 
        </td>
        <tr>
            <td>C1)null </td>
                <td>null</td>
        </tr>
        <tr>
            <td>C2)not null</td>
                <td>"name"</td>
        </tr>
        <td rowspan="3">D)length
        </td>
            <tr>
                <td>D1)empty </td>
                    <td>""</td>
            </tr>
            <tr>
                <td>D2)long </td>
                    <td>"Name is very long"</td>
            </tr>
    </tr>
    <tr>
        <td rowspan="6">qualifications</td>
            <td rowspan="3">E) null/notnull</td>
        <tr>
        <td>E1)null </td>
            <td>null</td>
        </tr>
        <tr>
        <td>E2)not null </td>
            <td>[description:"not null"]</td>
        </tr>
        <tr> 
            <td rowspan="3">F) length</td>
        </tr>
        <tr>
        <td>F1)empty </td>
            <td>[]</td>
        </tr>
        <tr>
        <td>F2)long </td>
            <td>[["long"],["qualified"]]</td>
        </tr>
    </tr>
    <tr>
        <td rowspan="8">Size</td>
            <td rowspan="4">G)type name</td>
        <tr>
        <td>G1)SMALL </td>
            <td>ProjectSize.SMALL</td>
        </tr>
        <tr>
        <td>G2)MEDIUM </td>
            <td>ProjectSize.MEDIUM</td>
        </tr>
        <tr>
        <td>G3) BIG</td>
            <td>ProjectSize.BIG</td>
        </tr>
        <tr> 
            <td rowspan="4">H) type int</td>
        </tr>
        <tr>
        <td>H1) 1 </td>
            <td>1</td>
        </tr>
        <tr>
        <td>H2) 2</td>
            <td>2</td>
        </tr>
        <tr>
        <td>H3) 3 </td>
            <td>3</td>
        </tr>
    </tr>
    </table>
    </body>

</html>
