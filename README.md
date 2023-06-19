# JavaDialyProject

## how to use
http://localhost:8080/javaDialyProject-0.0.1-SNAPSHOT/main

## DB
number,date,author,content,check
1,2022-01-01:12,sigma,hogehoge,11

レコードを一意に定める主キー
- author
- content
- check
- date

主キー以外に依存関係のあるキーをまとめる
author

number,content,check,date


## mysql
- table名は大文字小文字の区別あり。

```sql
CREATE DATABASE diary;
USE diary;
CREATE TABLE account (NUMBER SERIAL PRIMARY KEY, ID char(255), PASSWORD char(255));
INSERT INTO account (ID,PASSWORD) VALUES('hoge','password');
SELECT * FROM account;
+--------+------+----------+
| NUMBER | ID   | PASSWORD |
+--------+------+----------+
|      1 | hoge | password |
+--------+------+----------+

CREATE TABLE diarycontent (NUMBER SERIAL PRIMARY KEY, dt DATETIME, id CHAR(255), sentence TEXT(2048), checks CHAR(255));
INSERT INTO diarycontent (dt, id, sentence, checks) VALUES('1999-12-31', 'hoge', '僕は耳と目を閉じ，口を噤んだ人間になろうと考えた', NULL);
SELECT * FROM diarycontent;
+--------+---------------------+------+--------------------------------------------------------------------------+--------+
| NUMBER | dt                  | id   | sentence                                                                 | checks |
+--------+---------------------+------+--------------------------------------------------------------------------+--------+
|      3 | 1999-12-31 00:00:00 | hoge | 僕は耳と目を閉じ，口を噤んだ人間になろうと考えた                         | NULL   |
+--------+---------------------+------+--------------------------------------------------------------------------+--------+
DELETE FROM diarycontent WHERE dt='1999-12-31 00:00:00';

# 権限付与
GRANT ALL ON diary.* TO hoge;
SHOW GRANTS FOR hoge;

# テーブルのデータの全削除
TRUNCATE [TABLE] tbl_name 
```
