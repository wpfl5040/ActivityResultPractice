# ActivityResult API
 - 기존 방법 : startActivityForResult()의 호출과 onActivityResult(requestCode, resultCode, data) 콜백호출
 - Activity Result API : 시스템에서 전달되면 결과를 등록, 실행 및 처리하기 위한 구성요소를 제공합니다.
 
 
## 장점
 - 디커플링 및 관심사 분리 : 기존 액티비티 또는 프레그먼트의 onActivityResult에서 if와 else if로 도배되던 비즈니스 로직들이 콜백메서드 또는 분리된 클래스 단위로 쪼개어져서 관리될 수 있다.
 이는 코드의 가독성을 높이고, 유닛테스트를 수월하게 하며, 유지보수측면에서도 많은 도움이 된다.
 - Type-Safety : ActivityResultContract는 입력 데이터와 출력 데이터의 타입을 강제하기 때문에 잘못된 타입으로 캐스팅하는 사소한 실수를 미연에 방지시켜준다.
 - NPE 방지 : Intent로 부터 데이터를 얻으려고 할 때 NullPointerException이 발생하는 경험을 누구나 한번쯤은 해보았을 것이다. 새로운 API는 NPE가 발생할 확률을 줄여줄 것이다.

## set up
```
  implementation "androidx.activity:activity:1.2.0-alpha08"
  implementation "androidx.fragment:fragment:1.3.0-alpha08"
```

## Activity Result 요약
 - Contract 생성하기 : ActivityResultContract 상속한 서브 클래스 만들기
 - Contract 등록하기 : registerForActivityResult 메서드를 호출하여 Contract를 등록, 등록된 Contract는 Launcher를 반환
 - Launcher로 액티비티 실행하기 : ActivityResultLaunchor를 통해 launch 메서드를 호출

 
## Contract 정의
```
abstract class ActivityResultContract<I, O> {
    abstract Intent createIntent(@NonNull Context context, I input);
    abstract O parseResult(int resultCode, @Nullable Intent intent);
}
```
- createIntent 메서드 : 다른 액티비티를 호출하기 위한 Intent를 생성한다. 제네릭 타입 I가 intent를 생성하기 위한 매개변수 타입으로 전달된다. (startActivityForResult 메서드 호출을 대체한다.)
- parseResult 메서드 : 액티비티로 전달받은 결과 데이터를 제너릭 O타입으로 변환한다. (onActivityResult 콜백 메서드 처리를 대체한다.)


## permission 체크
```
registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
                if(isGranted) toast("퍼미션 허용됨")
                else toast("퍼미션 거부됨")
            }.launch(Manifest.permission.ACCESS_FINE_LOCATION)
```

## startActivity(...forresult)
```
registerForActivityResult(SecondActivityContract()){ result ->
                if(!result.isNullOrBlank()) toast("Result $result")
                else toast("Not Result")
            }.launch(input)
```


# 참고 문서 
- https://developer.android.com/training/basics/intents/result?hl=ko
- https://proandroiddev.com/is-onactivityresult-deprecated-in-activity-results-api-lets-deep-dive-into-it-302d5cf6edd
- https://medium.com/@charlezz/%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EA%B2%B0%EA%B3%BC-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0-good-bye-startactivityforresult-onactivityresult-82bafc50edac
