# 2024 02 29 複習類別

### 基本概念

- 至少一個類別
- 由資料成員與函式成員
- 類別內的資料成員稱為field(範疇)
- 在OOP裡，函式成員是封裝在類別之內
``` typeScript
class `${[A-Z]...}` { //開頭大寫(通俗 -> 為與其他變數作區別)
    "data member":[
        ...(屬性、field)
    ]
    "function member":[
        ...
    ]
}
```
e.g.
``` java
class Rectangle{
    //data member
    private int width;
    private int height;

    //function mamber
    public int area(){
        return width*height;
    }
    public int perimeter(){
        return 2*(width+height);
    }
}
```
---
### instance
- 由類別建立的物件稱為該類別的instance
- 使用 "new" 建立物件(宣告時未指派的話，只會先給定記憶體空間)
---
### 複習上學期 final exam ( 112_1 )
refer to reviewFinal.java