package learn.luckyyou.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import learn.luckyyou.SecretSigningKey;
import learn.luckyyou.domain.Result;
import learn.luckyyou.domain.UsersService;
import learn.luckyyou.models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
    private final UsersService service;
    private final SecretSigningKey secretSigningKey;

    public UsersController(UsersService service, SecretSigningKey secretSigningKey) {
        this.service = service;
        this.secretSigningKey = secretSigningKey;
    }

    @GetMapping
    public List<Users> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Users user)  {
        Result<Users> result = service.add(user);

        if (result.isSuccess()) {
            HashMap<String, String> jwt = getJwtFromUser(result.getPayload());
            return new ResponseEntity<>(jwt, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@RequestBody Users user, @PathVariable int userId
//                                    @RequestHeader HashMap<String, String> headers
    ) {
//        if (userId != user.getUserId()) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }

//        Integer userId = getUserIdFromHeaders(headers);

//        if (userId == null) {
//            return new ResponseEntity<>(List.of("Authentication failed"), HttpStatus.FORBIDDEN);
//        }

        Users existingUser = service.findById(userId);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//
//        if (existingUser.getUserId() != userId) {
//            return new ResponseEntity<>(List.of("You cannot edit a panel you don't own"), HttpStatus.FORBIDDEN);
//        }
//
//        solarPanel.setUserId(userId);
//        Result<SolarPanel> result = service.update(solarPanel);
//        if (!result.isSuccess()) {
//            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204

        Result<Users> result = service.update(user);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
        if (!result.isSuccess()) {
            return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

//        return ErrorResponse.build(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user)  {
        Users existingUser = service.findByUsername(user.getUserName());

        if (existingUser == null) {
            return new ResponseEntity<>(List.of("Username could not be found"), HttpStatus.NOT_FOUND);
        } else if (BCrypt.verifyer().verify(user.getPassword().toCharArray(), existingUser.getPassword()).verified) {
            HashMap<String, String> jwt = getJwtFromUser(existingUser);
            return new ResponseEntity<>(jwt , HttpStatus.OK);
        } else {
            return new ResponseEntity<>(List.of("Invalid password"), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable int userId) {
        Users existingUser = service.findById(userId);
        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       service.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private HashMap<String, String> getJwtFromUser(Users user) {
        String jwt =  Jwts.builder()
                .claim("user_name", user.getUserName())
                .claim("user_id", user.getUserId())
                .signWith(secretSigningKey.getKey())
                .compact();
        HashMap<String, String> output = new HashMap<>();
        output.put("jwt", jwt);
        return output;
    }

    private Integer getUserIdFromHeaders(HashMap<String, String> headers) {
        if (headers.get("authorization") == null) {
            return null;
        }

        Integer userId;
//        try {
            Jws<Claims> parsedJwt = Jwts.parserBuilder()
                    .setSigningKey(secretSigningKey.getKey())
                    .build().parseClaimsJws(headers.get("authorization"));
            userId = (Integer) parsedJwt.getBody().get("userId");
//        } catch (SignatureException ex) {
//            return null;
//        }

        return userId;
    }
}