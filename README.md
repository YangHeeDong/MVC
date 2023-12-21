## 서비스명
Spring IoC Container 구현하여 간단한 MVC 게시판 만들기

### 서비스 설명
Spring IoC Container를 Spring을 쓰지 않고 직접 구현하여 <br />
IoC와 DI의 개념을 공부하고 이해 해보자
<br>
## 🛠 개발환경
- Java (Intellij)
- MySql (DBeaver)
- Jsp (Jquery, BootStrap)
<br>
## ☁️ ERD

![초급프로젝트_ERD](https://i.imgur.com/NuLQFMU.png)

<br>
<br>

## 👀 시연영상
[![이미지 텍스트](스크린샷 이미지)](유투브링크)

[![Video Label](http://img.youtube.com/vi/'유튜브주소의id'/0.jpg)](https://youtu.be/'유튜브주소의id')
<br>
## 🔥 트러블 슈팅

### 🚨 Issue 1
### 🚧 이슈 제목
filter를 사용해야 하나 말아야 하나.<br /><br />

A. 이슈 내역 <br />
글 작성,수정, 삭제 등 로그인이 필요한 서비스는 로그인 확인이 필요해 <br /><br />

 ## 🛑 문제점 설명 <br />
현재 구조
- Dispatucre servlet에서 요청을 캡쳐하여 ControllerManager로 보냄
- ControllerManager에서 URL 분석 후 Container에 맞는 요청으로 처리
- 로그인 확인을 하기 위해서는 HttpServelt을 이용해야하는데, HttpServlet은 현재 Rq라는 객체에 포함되어 관리되고 있음.
- Rq는 Controller Manager에서 사용중

## 🚥 해결
- 굳이 filter를 사용하지 않고 ControllerManager에서 URL 분석시 로그인이 필요하면 해당 요청을 Login 요청으로 바꿔 Controller로 보냄
- 왜? ControllerManager에서 사용하는 Rq 객체가 모든 HttpServlet 관련 관리를 하고 있어, 유지보수를 편하게 하기 위함.
<br><br>


### 🚨 Issue 2
### 🚧 현재 구현된 Object Mapper 사용시 유의할 점
DB에서 데이터를 가져와 DTO에 매핑하기 위해서 objectMapper를 사용할때의 에러
<br>
<br>
## 🛑 원인 <br>
- Object Mapper에서 DTO에 DB 데이터를 매핑할 때 DTO class에 @AllArgsConstructor를 붙히면 에러가 남.
- @AllArgsConstructor는 선언된 모든 변수를 파라미터로 가지는 생성자임.
- 현재 Article DTO에는 aritcle에 해당하는 DB 데이터 뿐 아니라, 다른 데이터도 담는 변수가 선언되어있음
<br>

## 🚥 해결
- @AllArgsConstructor을 쓰지 않고 @Data 어노테이션을 쓰니 해결되었다.
