
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  <link rel="stylesheet" href="/css/login.css">
  <title>Login Page</title>
</head>
<body>
<div class="container-lg">
  <nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist">
      <button class="nav-link active" id="nav-teachers-tab" data-bs-toggle="tab" data-bs-target="#nav-teachers" type="button" role="tab" aria-controls="nav-teachers" aria-selected="true">Teachers</button>
      <button class="nav-link" id="nav-student-tab" data-bs-toggle="tab" data-bs-target="#nav-students" type="button" role="tab" aria-controls="nav-students" aria-selected="false">Students</button>
      <button class="nav-link" id="nav-users-tab" data-bs-toggle="tab" data-bs-target="#nav-users" type="button" role="tab" aria-controls="nav-users" aria-selected="false">Users</button>
      <button class="nav-link" id="nav-cities-tab" data-bs-toggle="tab" data-bs-target="#nav-cities" type="button" role="tab" aria-controls="nav-cities" aria-selected="false">Cities</button>
      <button class="nav-link" id="nav-specialities-tab" data-bs-toggle="tab" data-bs-target="#nav-specialities" type="button" role="tab" aria-controls="nav-specialities" aria-selected="false">Specialities</button>
      <button class="nav-link" id="nav-meetings-tab" data-bs-toggle="tab" data-bs-target="#nav-meetings" type="button" role="tab" aria-controls="nav-meetings" aria-selected="false">Meetings</button>
    </div>
  </nav>
  <div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-teachers" role="tabpanel" aria-labelledby="nav-teachers-tab" tabindex="0">
      <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
          <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" >
            <form method="post" action="${pageContext.request.contextPath}/schoolapppro/teachersearch">
              <div class="input-group mb-4">
                <input name="lastname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Lastname">
              </div>
              <div class="input-group ">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Search Teacher</button>
              </div>
            </form>
          </div>
          <div class="col-md-6 right-box">
            <div class="row align-items-center">
              <form method="post" action="${pageContext.request.contextPath}/schoolapppro/teacherinsert">
                <div class="input-group mb-3">
                  <input name="firstname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Firstname">
                </div>
                <div class="input-group mb-3">
                  <input name="lastname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Lastname">
                </div>
                <div class="input-group mb-3">
                  <input name="ssn" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Ssn">
                </div>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="speciality">Speciality</label>
                  <select name="speciality" class="form-select" id="speciality">
                    <c:forEach var="speciality" items="${requestScope.specialities}">
                      <option value="${speciality.id}"> ${speciality.speciality} </option>
                    </c:forEach>
                  </select>
                </div>
                <div class="input-group mb-3">
                  <input name="userid" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="User Id">
                </div>
                <div class="input-group mb-2">
                  <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert Teacher</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="tab-pane fade" id="nav-students" role="tabpanel" aria-labelledby="nav-students-tab" tabindex="0">
      <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
          <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <form method="post" action="${pageContext.request.contextPath}/schoolapppro/studentsearch">
              <div class="input-group mb-4">
                <input name="lastname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Lastname">
              </div>
              <div class="input-group ">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Search Student</button>
              </div>
            </form>
          </div>
          <div class="col-md-6 right-box">
            <div class="row align-items-center">
              <form method="post" action="${pageContext.request.contextPath}/schoolapppro/studentinsert">
                <div class="input-group mb-3">
                  <input name="firstname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Firstname">
                </div>
                <div class="input-group mb-3">
                  <input name="lastname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Lastname">
                </div>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="gender">Gender</label>
                  <select name="gender" class="form-select" id="gender">
                    <option value="1">Male</option>
                    <option value="2">Female</option>
                  </select>
                </div>
                <div class="input-group mb-3">
                  <input name="birthdate" type="date" class="form-control form-control-lg bg-light fs-6" placeholder="Birthdate">
                </div>
                <div class="input-group mb-3">
                  <label class="input-group-text" for="city">City</label>
                  <select name="city" class="form-select" id="city">
                    <c:forEach var="city" items="${requestScope.cities}">
                      <option value="${city.id}"> ${city.city} </option>
                    </c:forEach>
                  </select>
                </div>
                <div class="input-group mb-3">
                  <input name="userid" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="User Id">
                </div>
                <div class="input-group mb-2">
                  <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert Student</button>
                </div>
              </form>
            </div>
          </div>
        </div>


      </div>
    </div>
    <div class="tab-pane fade" id="nav-users" role="tabpanel" aria-labelledby="nav-users-tab" tabindex="0">

      <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
          <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" >
            <form method="post" action="${pageContext.request.contextPath}/schoolapppro/usersearch">
              <div class="input-group mb-4">
                <input name="username" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Username">
              </div>
              <div class="input-group ">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Search User</button>
              </div>
            </form>
          </div>
          <div class="col-md-6 right-box">
            <div class="row align-items-center">
              <form method="post" action="${pageContext.request.contextPath}/schoolapppro/userinsert">
                <div class="input-group mb-3">
                  <input name="username" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Username">
                </div>
                <div class="input-group mb-3">
                  <input name="password" type="password" class="form-control form-control-lg bg-light fs-6" placeholder="Password">
                </div>
                <div class="input-group mb-2">
                  <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert User</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>


    </div>
    <div class="tab-pane fade" id="nav-cities" role="tabpanel" aria-labelledby="nav-cities-tab" tabindex="0">

      <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
          <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" >
            <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/citysearch">
              <div class="input-group mb-4">
                <input name="city" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="City Name">
              </div>
              <div class="input-group ">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Search City</button>
              </div>
            </form>
          </div>
          <div class="col-md-6 right-box">
            <div class="row align-items-center">
              <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/cityinsert">
                <div class="input-group mb-3">
                  <input name="city" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="City Name">
                </div>

                <div class="input-group mb-2">
                  <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert City</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

    </div>
    <div class="tab-pane fade" id="nav-specialities" role="tabpanel" aria-labelledby="nav-specialities-tab" tabindex="0">
      <div class="container d-flex justify-content-center align-items-center min-vh-100">
        <div class="row border rounded-5 p-3 bg-white shadow box-area">
          <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" >
            <form method="get" action="${pageContext.request.contextPath}/shcoolapppro/allspecialities">
              <div class="input-group mb-3">
                <input name="speciality" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Speciality">
               </div>
              <div class="input-group ">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Show Specialities</button>
              </div>
            </form>
          </div>
          <div class="col-md-6 right-box">
            <div class="row align-items-center">
              <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/specialityinsert">
                <div class="input-group mb-3">
                  <input name="speciality" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Speciality">
                </div>
                <div class="input-group mb-2">
                  <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert Speciality</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="nav-meetings" role="tabpanel" aria-labelledby="nav-meetings-tab" tabindex="0"><div class="container d-flex justify-content-center align-items-center min-vh-100">
      <div class="row border rounded-5 p-3 bg-white shadow box-area">
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box" >
          <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/meetingsearch">
            <div class="input-group mb-3">
              <input name="lastname" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Teacher's Lastname">
            </div>
            <div class="input-group ">
              <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Search for Meetings</button>
            </div>
          </form>
        </div>
        <div class="col-md-6 right-box">
          <div class="row align-items-center">
            <form method="post" action="${pageContext.request.contextPath}/shcoolapppro/meetinginsert">
              <div class="input-group mb-3">
                <input name="teacherid" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Teacher's Id">
              </div>
              <div class="input-group mb-3">
                <input name="studentid" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Student's Id">
              </div>
              <div class="input-group mb-3">
                <input name="date" type="date" class="form-control form-control-lg bg-light fs-6" placeholder="Meeting Date">
              </div>
              <div class="input-group mb-3">
                <input name="meetingroom" type="text" class="form-control form-control-lg bg-light fs-6" placeholder="Meeting Room">
              </div>
              <div class="input-group mb-2">
                <button type="submit" class="btn btn-lg btn-primary w-100 fs-6">Insert Meeting</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</body>
</html>