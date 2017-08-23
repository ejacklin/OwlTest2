USE owl_schema;

DELETE FROM user WHERE first_name='Tyrion';
DELETE FROM user WHERE first_name='Jon';
DELETE FROM user WHERE first_name='Denarys';
DELETE FROM exercise WHERE workout_id=1;
DELETE from session where session_id = 3;

ALTER TABLE user AUTO_INCREMENT =1;
ALTER TABLE session AUTO_INCREMENT =1;
ALTER TABLE workout AUTO_INCREMENT =1;
ALTER TABLE exercise AUTO_INCREMENT =1;
ALTER TABLE cardio AUTO_INCREMENT =1;
ALTER TABLE strength AUTO_INCREMENT =1;

ALTER TABLE workout DROP FOREIGN KEY workout_user_user_id_fk;
ALTER TABLE exercise DROP INDEX exercise_workout_id_uindex;

SELECT * from workout
where workout_id =
      (SELECT * from session
        where session.session_id =
              (SELECT * from user
              WHERE username="jsnow"));

INSERT INTO owl_schema.user (username,first_name, last_name, age, weight, gender,password, email) VALUES ('jsnow','Jon', 'Snow',27,175,'male','ghost','jsnow@gmail.com');
INSERT INTO owl_schema.workout(name,user_id) VALUES ('War Day',(SELECT user.user_id from user where username="jsnow"));
INSERT INTO owl_schema.session( date, user_id, workout_id) VALUES ('2017-08-15', (SELECT user.user_id from user where first_name = 'Jon'),(SELECT workout_id from workout where name = 'War Day'));
INSERT INTO owl_schema.exercise(name, type, workout_id) VALUES ('Sword Lifts', 'strength',(SELECT workout.workout_id FROM workout WHERE name='War Day'));
INSERT INTO owl_schema.strength(reps, sets, weight, exercise_id) VALUES (12,3,200, (SELECT exercise.exercise_id FROM exercise WHERE name='Sword Lifts'));
INSERT INTO owl_schema.exercise(name, type, workout_id) VALUES ('Whitewalker Run', 'cardio',(SELECT workout.workout_id FROM workout WHERE name='War Day'));
INSERT INTO owl_schema.cardio(distance,time, exercise_id) VALUES (7,60, (SELECT exercise.exercise_id FROM exercise WHERE name='Whitewalker Run'));

INSERT INTO owl_schema.user (username,first_name, last_name, age, weight, gender,password,email) VALUES ('tlanister','Tyrion', 'Lanister',37,125,'male','shea','tlanister@gmail.com');
INSERT INTO owl_schema.workout(name, user_id) VALUES ('Murder Day', (SELECT user.user_id FROM user WHERE username="tlanister"));
INSERT INTO owl_schema.session( date, user_id,workout_id) VALUES ('2017-08-18', (SELECT user.user_id from user where first_name = 'Tyrion'),(SELECT workout.workout_id from workout where name="Murder Day"));
INSERT INTO owl_schema.exercise(name, type, workout_id) VALUES ('Crossbow Shots', 'strength',(SELECT workout.workout_id FROM workout WHERE name='Murder Day'));
INSERT INTO owl_schema.strength(reps, sets, weight, exercise_id) VALUES (1,5,14, (SELECT exercise.exercise_id FROM exercise WHERE name='Crossbow Shots'));
INSERT INTO owl_schema.exercise(name, type, workout_id) VALUES ('Dancing', 'cardio',(SELECT workout.workout_id FROM workout WHERE name='Murder Day'));
INSERT INTO owl_schema.cardio(distance,time, exercise_id) VALUES (7,60, (SELECT exercise.exercise_id FROM exercise WHERE name='Dancing'));







SELECT * FROM user WHERE first_name='Erin';

LOAD DATA LOCAL INFILE 'C:\\Users\\Erin\\Desktop\\users.txt' INTO TABLE owl_schema.user;

SELECT user_id, AGE, FIRST_NAME, GENDER, LAST_NAME FROM user WHERE (user_id = 6);

SELECT session_id, date, user_id FROM session WHERE (session_id = 2);

TRUNCATE TABLE user;