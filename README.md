# ColorUtil
- - -
> 이 프로젝트는 라이브러리 입니다. 플러그인이 아닙니다.

Paper 버킷의 TextColor 클래스를 편리하게 사용하게 해주는 라이브러리 입니다.

## 🪶 Maven
- - -
```
<dependency>
    <groupId>io.github.10000je</groupId>
    <artifactId>ColorUtil</artifactId>
    <version>1.0</version>
</dependency>
```

## 🐘 Gradle
- - -
```
implementation 'io.github.10000je:ColorUtil:1.0'
```

## 사용방법
- - -
```
ColorUtil.translateColorCodes(char delimiter, String message)
```
- 기존의 버킷 라이브러리의 ChatColor와  유사하나, HexCode를 사용할 수 있습니다.
- `{delimiter}#00ff00Hello World` 로 작성하는 것이 가능합니다.
- 예를 들어, delimiter 를 '&'로 설정했다면, `&#00ff00Hello &cWorld.` 로 작성시 색깔이 적용됩니다.
- 그 외 함수들은 소스코드를 참조해 주십시오.

## 주의사항
- - -
Maven 은 따로 테스트해보지 않아서 모르겠으나,

Gradle 은 패키징시 의존성 라이브러리를 따로 포함하지 않습니다.

패키징을 위해선 shadow plugin을 사용하시길 권합니다.

<https://plugins.gradle.org/plugin/com.gradleup.shadow>