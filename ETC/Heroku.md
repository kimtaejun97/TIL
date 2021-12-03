# 🧐Heroku
***
- Heroku : https://dashboard.heroku.com/
- Heroku kaffeine : https://kaffeine.herokuapp.com/

- ### heroku remote 등록
```heroku git:remote -a {appname}```

- ### heroku restart
```heroku restart```

- ### heroku App log
```heroku logs --tail```

- ### heroku bash
```heroku run bash```

- ### heroku 환경변수 (민감한 정보를 properties에서 숨기기 위해 사용.)
> - heroku app -> settings -> config vars    
> - Project 환경변수와 사용 동일.
```properties
app.password = ${key}
```