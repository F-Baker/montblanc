#Name
- JwtAuthenticationFilter
#Description
- 
#Type
- class
#Implements
#Extends
- OncePerRequestFilter
#Autowired
- JwtUtils
#Overrides
- doFilterInternal
```
 protected abstract void doFilterInternal(HttpServletRequest var1, HttpServletResponse var2, FilterChain var3) throws ServletException, IOException;
 ```
#Methods
- doFilterInternal
    - takes:
        - HttpServletRequest,
        - HttpServletResponse,
        - FilterChain
            - All three are a part of the javax.servlet package
    - throws:
        - ServletException
        - IOException
    - uses:
        - parseJwt()
        - JwtUtils.getUserNameFromJwtToken
        - userDetailsService.loadByUsername
        - UserNamePasswordAuthenticationToken.setDetails
        - WebAuthenticationDetailsSource.buildDetails
        - SecurityContextHolder.getContext
        - SecurityContext.setAuthentication
        - Filterchain.doFilter
    - logic
        - overrides the initial method
        - enters a try/catch block
        - strips the "bearer " portion from the auth header with parseJwt
        - parses the verificationToken and sets the signin key with SECRET (SecurityConstants)
      


    - notes
    

- parseJWT
    - takes:
        - HttpServletRequest
        - StringUtils
    - throws:
    - uses: 
        - HttpServletRequest.getHeader()
        - StringUtils.hasText()
    - logic:
        - checks the header for "Authorization" tag
        - then using StringUtils, it parses the header, looking for the "Bearer " prefix
        - substring is used to strip "Bearer " after the 7th character (the intentional space)  
        - "Authorization" has been abstracted to the SecurityConstants
    - notes  
        - the example doesn't have any error handling which would be a good thing since the header/prefix have been abstracted


