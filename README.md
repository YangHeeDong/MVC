## 서비스명
Spring IoC Container 구현하여 간단한 MVC 게시판 만들기

### 서비스 설명
Spring IoC Container를 Spring을 쓰지 않고 직접 구현하여 <br />
IoC와 DI의 개념을 공부하고 이해 해보자

## 🛠 개발환경
- Java (Intellij)
- MySql (DBeaver)
- Jsp (Jquery, BootStrap)

## ☁️ ERD

![초급프로젝트_ERD](https://i.imgur.com/NuLQFMU.png)

<br>
<br>

## 👀 시연영상
[![이미지 텍스트](스크린샷 이미지)](유투브링크)

[![Video Label](http://img.youtube.com/vi/'유튜브주소의id'/0.jpg)](https://youtu.be/'유튜브주소의id')

## 🔥 트러블 슈팅

### 🚨 Issue 1
### 🚧 이슈 제목
Interceptor를 구현해야 하나 말아야 하나.<br /><br />

A. 이슈 내역 <br />
글 작성,수정, 삭제 등 로그인이 필요한 서비스는 로그인 확인이 필요해 <br /><br />

문제점 설명 <br />
Dispatucre servlet에서 요청을 캡쳐하여 ControllerManager에서 URL 분석하여 Container 스캔 후 <br />

## 🛑 원인
- ...
<br>
<br>

## 🚥 해결
- ...

### 🚨 Issue 2
### 🚧 현재 구현된 Object Mapper 사용시 유의할 점

<br>
DB에서 데이터를 가져와 DTO에 매핑하기 위해서 objectMapper를 사용할때의 에러
<br>
## 🛑 원인
- Object Mapper에서 DTO에 DB 데이터를 매핑할 때 DTO class에 @AllArgsConstructor를 붙히면 에러가 남.
- @AllArgsConstructor는 선언된 모든 변수를 파라미터로 가지는 생성자임.
- 현재 Article DTO에는 aritcle에 해당하는 DB 데이터 뿐 아니라, 다른 데이터도 담는 변수가 선언되어있음

<br>
<br>

## 🚥 해결
- @AllArgsConstructor을 쓰지 않고 @Data 어노테이션을 쓰니 해결되었다.
