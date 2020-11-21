# Banking API

<h3>Product Description</h3>
<hr>
This is a backend API server build mainly using Java servlet which privdes an interaction interface between the user and database storing the bank information.

<h3>Database Schema</h3>
<hr>
The bank database consist of 3 tables with one-to-many relationship between them as show below

!["DB Schema"](/Demo/schema.png)

- One branch has many users
- One user has many accounts

<h3>API Description</h3>
<hr>

<h4><strong>Login User</strong></h4>
Authenticate the user with email/password credentials and receive JWT token back if the user authenticated <br>
POST: http://localhost:8080/Bank_P0/login
Post Body: x-www-form-urlencoded <br>
<table>
<tr>
<th>Key</th>
<th>Value</th>
</tr>
<tr>
<td>username</td>
<td>test@gmail.com</td>
</tr>
<tr>
<td>password</td>
<td>123</td>
</tr>
</table>
<br>
Result Body: <br>
Welcome test@gmail.com >> admin <br>
JWT Token: <br>
eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxNCIsInJvbGUiOiJhZG1pbiIsImVtYWlsIjoidGVzdEBnbWFpbC5jb20iLCJleHAiOjE2MDYwMTIxNDQsImlhdCI6MTYwNTk5Nzc0NH0.04aOvPsUukU2Zbwq7yfGLyFlXFnvxqcB22SyTrA4C7g
<br>
***
