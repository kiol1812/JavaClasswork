## 檔案大小比較

將boolean, byte, short, int, char, long, float, double等八個原始資料型態各取一個值，以文檔和二元檔分別儲存，觀察其檔案大小差異。
注意要寫出個別格式讀取的程式，以確認寫出的數值可以被正確讀回。

## InputStream, OutputStream
- 處理二進制檔
## Reader, Writer
- 處理文字檔

```java
import java.io.*;
```

### Reader
- close()
- read()
- read(char cbuf[])
- read(cahr cbuf[], int off, int len) #off為索引
- ...

### Writer
- close()
- flash()
- ...


## 回家作業寫 棋類遊戲的存檔



