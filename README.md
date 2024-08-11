# Spring security based user authentication service.

I am working on this practice project to showcase my learning towards Spring's security project. I have implemented following features as of now

features : 

main-branch: 
- Implemented an endpoint("/hello") which is secured with security-filter-chain
- basic-auth with In-Memory UserDetailService(provided out-of-the-box by spring security) for maintaining user details and roles info.

feature/user_details_with_h2
- H2 based User detail store which stores password with BCrypt encoder
- End-point to register user details [("/register") which returns encrypted token.
- One more endpoint "/validate" to validate token returned preiously.
  
