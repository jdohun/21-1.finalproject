21-1.finalproject
===
# MVC2 model 과 css, js 를 응용한 쇼핑몰 웹사이트 프로젝트
  + 굳이 3개의 컨트롤러로 나눈 이유는 만약 사용자가 단순 회원 정보만 고치거나 충전만 미리 해놓는다던가<br> 다른 기능을 사용하지 않았을 때는 굳이 초기화할 필요가 없는 기능들을 초기화하지 않음으로써 최적화에 신경을 써봤습니다.

## 3가지의 주요 컨트롤러
  ### FrontContoller : 사용자 정보와 관련된 것들(\*.do)을 담당하는 중앙 컨트롤러
  * SignUpController
  * LoginController
  * ChargeController
  * ModifyController  

  ### ShowController : 보여지는 정보와 관련된 것들(\*.show)을 담당하는 중앙 컨트롤러
  * ShowAllController : 메인화면에 보이는 상품들의 정보를 전부 가져온다.
  * ShowDescriptionController : 상품번호를 통해 상품 상세페이지를 불러옴
  * ShowCartController : 장바구니에 담겨있는 상품리스트를 보여준다.
  * ShowCartAllOrderController : 장바구니에서 전체 주문 클릭시 주문페이지에 전체 상품을 보여준다.
  * ShowOrderFormController : 상품 상세페이지에서 바로 구매했을 때 주문페이지를 보여준다.
  * ShowDeleteCartController : 장바구니에서 상품 삭제 후 장바구니를 다시 보여준다
  * ShowSelectedOrderForm : 장바구니에서 선택한 상품들을 주문페이지에서 보여준다.
  * ShowOrderCompletedController : 주문이 완료되면 주문번호, 주문시각, 주문시 입력했던 정보들(주문 상세내역)을 보여준다.
  * ShowOrderList : 주문 내역들을 보여준다.
  * ShowOrderDetail : 주문내역에서 주문 번호를 클릭 시 주문 상세내역을 보여준다.

### OrderController : 주문, 장바구니와 같은 주문 정보와 관련된 것들(\*.order)를 담당하는 중앙 컨트롤러
  * OrderOnListController : 상품 상세페이지에서 장바구니에 넣을 상품들을 처리하는 컨트롤러
  * OrderCompletedController : 주문페이지에서 주문을 처리하는 컨트롤러
***
* src > com.dev.vo
  * UserVO : 사용자 정보를 담고 있는 객체
  * ItemVO : 상품 정보를 담고 있는 객체
  * CartVO : 장바구니 정보를 담고 있는 객체
  * OSheetVO : 주문 정보를 담고 있는 객체
  * ODetailVO : 주문 상세 정보를 담고 있는 객체

* src > com.dev.service
  * UserService : 사용자 정보와 관련된 처리를 하는 service
  * ItemService : 상품 정보와 관련된 처리를 하는 service
 
* src > com.dev.dao
  * UserDAO : 사용자 정보와 관련된 처리를 하는 service
  * ItemDAO : 상품 정보와 관련된 처리를 하는 service
***
WebContent - css - \*.css files <br>
WebContent - images - \*.jpg or \*.png <br>
WebContent - js - \*.js <br>
WebContent - \*.jsp <br>
