# ๐ Index
***

## ๐ง ์ธ๋ฑ์ค๋?
![img.png](img/img_8.png)         
์ด๋ฏธ์ง ์ถ์ฒ: https://mangkyu.tistory.com/96   

์ธ๋ฑ์ค๋ ๋ฐ์ดํฐ๋ฒ ์ด์ค์์ ์ง์ ํ ์ปฌ๋ผ์ ๊ธฐ์ค์ผ๋ก ๋ฉ๋ชจ๋ฆฌ ์์ญ์ ๋ชฉ์ฐจ๋ฅผ ์์ฑํ๋ค๊ณ  ์ดํดํ๋ฉด ์ฝ๋ค. ์ฆ, ์กฐํ์ ์๋๋ฅผ ํฅ์์ํค๊ธฐ ์ํด ์ฌ์ฉ๋๋ค.
์ธ๋ฑ์ค๋ฅผ ํตํด ์ฐพ๊ณ ์ ํ๋ ๋ฐ์ดํฐ๋ฅผ ์ฐพ์ ํ ํด๋น ๋ฐ์ดํฐ์์ ์ค์  ๋ฌผ๋ฆฌ ๋ฉ๋ชจ๋ฆฌ ์ฃผ์๋ฅผ ์ป์ด, ๋์คํฌ์ ์ ๊ทผํ๋ค.

ํ์ง๋ง "๋ฉ๋ชจ๋ฆฌ ์์ญ"์ ์ ์ฅํ๊ธฐ ๋๋ฌธ์ ์ฉ๋์ ์ฝ 10%๋ฅผ ์ถ๊ฐ์ ์ธ ๊ณต๊ฐ์ด ํ์ํ๋ค. ๋ํ ํญ์ ์ ๋ ฌ๋ ์ํ๋ฅผ ์ ์งํด์ผ ํ๊ธฐ ๋๋ฌธ์
์ฝ์, ์ญ์ , ์์ ์ ์ถ๊ฐ์ ์ธ ๋น์ฉ์ด ๋ฐ์๋๋ค. ๋ํ ์ธ๋ฑ์ค๊ฐ ๋๋ฌด ๋ง๋ค๋ฉด ์๋ก์ด ๋ฐ์ดํฐ๋ฅผ ์ถ๊ฐํ  ๋๋ง๋ค ์ธ๋ฑ์ค๋ฅผ ์ถ๊ฐํด์ผ ํ๊ธฐ ๋๋ฌธ์ ์ฑ๋ฅ์ ์ ํ์ํจ๋ค.

ํ๋ฒ์ ๋ง์ ์์ ๋ฐ์ดํฐ๋ฅผ ์กฐํํ  ๊ฒ์ด๋ผ๋ฉด ์คํ๋ ค ํ์ด๋ธ ํ ์ค์บ์ด ๋ ์ ๋ ดํ  ์๋ ์๋ค. ์ผ๋ฐ์ ์ผ๋ก ์ฝ 5~15% ์ด์์ ๋ฐ์ดํฐ๋ฅผ ์กฐํํ  ๊ฒ์ด๋ผ๋ฉด
์ธ๋ฑ์ค๋ฅผ ์ฌ์ฉํ์ง ์๋ ํธ์ด ์ฑ๋ฅ์ด ๋ ์ ๋์จ๋ค.(์ด๋ ๋ฐ์ดํฐ์ ์์ ๋ฐ๋ผ ๋ค๋ฅด๋ค.) ํ์ง๋ง ์ด๋ฐ๊ฒฝ์ฐ ๋๋ํ ์ตํฐ๋ง์ด์ ๊ฐ ์ธ๋ฑ์ค๋ฅผ ๋ฌด์ํ๊ณ  ํ ์ค์บ์ ๋๋ฆด ๊ฐ๋ฅ์ฑ์ด ๋๋ค.

์ฑ๋ฅ์ด ๋์ค์ง ์๋ ์ด์ ๋ ์ผ๋ฐ์ ์ธ B-Tree ์์๋ Single I/O๋ฅผ ์ฌ์ฉํ๊ธฐ ๋๋ฌธ์ธ๋ฐ, ๋ฐ์ดํฐ๋ฅผ ํ๋ ๊ฐ์ ธ์ฌ ๋๋ง๋ค ํ ๋ฒ์ I/O๊ฐ ๋ฐ์ํ๊ฒ ๋๋ค.
๋์คํฌ์ ์ ๊ทผ์ด ๋น๋ฒํ๊ฒ ์ผ์ด๋๋ค๋ฉด ๋น์ฐํ ์ฑ๋ฅ์ ์ ํ๋๋ค. ํ์ด๋ธ ํ ์ค์บ ๋ฐฉ์์ ์ธ๋ฑ์ค์ ๋ฌ๋ฆฌ Multi Block I/O๋ฅผ ์ฌ์ฉํ๊ธฐ ๋๋ฌธ์ ํ๋ฒ์ ์ฌ๋ฌ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ
๊ฐ์ ธ์ค๊ฒ๋๊ณ , ๊ทธ๋งํผ ๋์คํฌ์ ์ ๊ทผ์ด ์ค์ด๋ ๋ค. ์ด๋ฌํ ํธ๋ ์ด๋ ์คํ ์์์ ์กฐํํ  ๋ฐ์ดํฐ๊ฐ ์ฆ๊ฐํ  ์๋ก ์ธ๋ฑ์ค๋ก ์ป๋ ์ด์ ์ ์ค์ด๋ค๊ฒ ๋  ๊ฒ์ด๋ค.

- INSERT: ์ ๊ท ๋ฐ์ดํฐ์ ๋ํ ์ธ๋ฑ์ค ์ถ๊ฐ.
- DELETE: ์ญ์ ๋๋ ๋ฐ์ดํฐ์ ์ธ๋ฑ์ค๋ฅผ ๋งํฌ ์ฌ์ฉํ์ง ์๋๋ก ํจ.
- UPDATE: ๊ธฐ์กด์ ์ธ๋ฑ์ค๋ฅผ ๋งํฌํ๊ณ , ๊ฐฑ์ ๋ ๋ฐ์ดํฐ์ ๋ํด ์ธ๋ฑ์ค๋ฅผ ์ถ๊ฐ.

### โ๏ธ ์ธ๋ฑ์ค๋ฅผ ์ฌ์ฉํ๊ธฐ ์ข์ ๊ฒฝ์ฐ.
- ๊ท๋ชจ๊ฐ ํฐ ํ์ด๋ธ
- INSERT, UPDATE, DELETE๊ฐ ์์ฃผ ๋ฐ์ํ์ง ์๋ ์ปฌ๋ผ(์์ฃผ ๋ฐ์ํ๋ฉด ์ธ๋ฑ์ค์ ํฌ๊ธฐ๊ฐ ์ปค์ ธ ์ฑ๋ฅ์ด ์ ํ๋๋ค.)
- ๋ฐ์ดํฐ์ ์ค๋ณต๋๊ฐ ๋ฎ์ ์ปฌ๋ผ.(์นด๋๋๋ฆฌํฐ๊ฐ ๋์) - ์ฌ๋ฌ๊ฐ์ ์ปฌ๋ผ์ผ๋ก ๊ตฌ์ฑํ๋ค๋ฉด ์นด๋๋๋ฆฌํฐ๊ฐ ๋์ ์์์ ๋ฎ์ ์์ผ๋ก.
- WHERE ์ , JOIN, Order By ์ ์์ฃผ ์ฌ์ฉ๋๋ ์ปฌ๋ผ.
- ์ธ๋ํค๊ฐ ์ฌ์ฉ๋๋ ์ปฌ๋ผ.

### โ๏ธ ์ธ๋ฑ์ค ํ์ด๋ธ์ ์์ฑ
- ํ์ด๋ธ ์์ฑ์ PK๊ฐ ์ง์ ๋์๋ค๋ฉด PK๋ฅผ ๊ธฐ์ค์ผ๋ก ์ธ๋ฑ์ค ํ์ด๋ธ์ด ์๋์ผ๋ก ์์ฑ๋๋ค.
- ๋ค๋ฅธ ์ปฌ๋ผ์ผ๋ก ์ธ๋ฑ์ค ํ์ด๋ธ์ ์์ฑํ๊ณ  ์ถ๋ค๋ฉด ์ง์  ๋ช์ํ์ฌ ์์ฑํ๋ฉด ๋๋ค.
 ```sql
ALTER TABLE table_name ADD INDEX index_name (col1, col2..)
```

์ธ๋ฑ์ค์ ํ์์ B-Tree์ ๊ฒฝ์ฐ Root - Branch - Leaf -> ๋์คํฌ ์ ์ฅ์ ์์ผ๋ก ์งํ๋๋ค.
๊ผญ Leaf ๋ธ๋๊น์ง ์ค์ง ์์๋ ๋๊ธฐ ๋๋ฌธ์ ์ฐพ๋ ๋ฐ์ดํฐ๊ฐ Root์ ๊ฐ๊น๊ฒ ์์์๋ก ํ์์๋๊ฐ ๋น ๋ฅด๋ค.
๋์คํฌ์์ ์ฝ์๋๋ ๋ฉ๋ชจ๋ฆฌ์์ ์ฝ์ ๋์ ๋นํด ํจ์ฌ ์ฑ๋ฅ์ด ๋จ์ด์ง๋ค. ๋๋ฌธ์ ์ธ๋ฑ์ค์ ์ฑ๋ฅ์
์ผ๋ง๋ ๋์คํฌ ์ ๊ทผ์ ์ค์ด๋. Root ~ Leaf์ ์ค๊ณ ๊ฐ๋ ํ์๋ฅผ ์ผ๋ง๋ ์ค์ด๋์ ๋ฌ๋ ค์๋ค.

์ธ๋ฑ์ค์ ์ปฌ๋ผ์ด ์ฌ๋ฌ๊ฐ๋ผ๋ฉด ๋ค์ ์ปฌ๋ผ์ ์์ ์ปฌ๋ผ์ ์์กดํด์ ์ ๋ ฌ๋๋ค. ์๋ฅผ๋ค์ด ์ด๋ฆ๊ณผ ์ ํ๋ฒํธ ์์๋ก ์ธ๋ฑ์ค ์ปฌ๋ผ์ ์ง์ ํ์๋ค๋ฉด.
์ด๋ฆ์ด ๊ฐ์ ๊ฒฝ์ฐ์๋ง ์ ํ๋ฒํธ๋ฅผ ๋น๊ตํ๊ฒ ๋๋ค.

MySQL์ InnoDB์์๋ ๋์คํฌ์ ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ๋ ๊ธฐ๋ณธ ๋จ์๋ฅผ ํ์ด์ง๋ผ๊ณ  ํ๋ฉฐ, ์ธ๋ฑ์ค ๋ํ ๋ง์ฐฌ๊ฐ์ง๋ก ๊ด๋ฆฌ๋๋ค.
ํ์ด์ง์ ํฌ๊ธฐ๋ 16KB๋ก ๊ณ ์ ๋์ด ์๊ธฐ ๋๋ฌธ์ ์ธ๋ฑ์ค ํค์ ํฌ๊ธฐ(key_len)์ ๋ฐ๋ผ ํ ํ์ด์ง์ ์ ์ฅ๋  ์ ์๋ ๋ฐ์ดํฐ์ ์๊ฐ ๋ฌ๋ผ์ง๋ค.
๋๋ฌธ์ ์ธ๋ฑ์ค ํค๊ฐ ๊ธธ์ด์ง ์๋ก ์ฑ๋ฅ์ ์ด์๊ฐ ๋ฐ์ํ๋ค.

### โ๏ธ ์ธ๋ฑ์ค ์ฌ์ฉ์ ์ฃผ์ํ  ์ .
- ์ธ๋ฑ์ค์ ์ปฌ๋ผ์ ๋ชจ๋ ์กฐํ์กฐ๊ฑด์ผ๋ก ์ฌ์ฉํด์ผ๋ง ์ธ๋ฑ์ค๊ฐ ์ฌ์ฉ๋๋ ๊ฒ์ ์๋๋ค. ํ์ง๋ง ์ธ๋ฑ์ค์ ์ปฌ๋ผ์ค ๊ฐ์ฅ ์ฒซ ๋ฒ์งธ ์ปฌ๋ผ์ ๋ฐ๋์ ํฌํจ๋์ด์ผ ํ๋ค.
์ฒซ ๋ฒ์งธ ์ปฌ๋ผ์ด ํฌํจ๋์ง ์์ผ๋ฉด ์ธ๋ฑ์ค๋ฅผ ํ์ง ์๋๋ค.

- ```between```, ```like```, ```<, >```(๋ถ๋ฑํธ) ๋ฑ์ ๋ฒ์ ์กฐ๊ฑด์ ๋ช์๋ ์ปฌ๋ผ์ ์ธ๋ฑ์ค๋ฅผ ํ์ง๋ง, ๊ทธ ๋ค์ ์ธ๋ฑ์ค ์ปฌ๋ผ๋ค์ ์ธ๋ฑ์ค๊ฐ ์ฌ์ฉ๋์ง ์๋๋ค.(```=```,```in```์ ๋ชจ๋  ์ปฌ๋ผ ์ฌ์ฉ)
- ```and``` ์ฐ์ฐ์ row๋ฅผ ์ค์ด์ง๋ง ```|``` ์ฐ์ฐ์ ๋น๊ตํ  row๋ฅผ ์ฆ๊ฐ์ํค๊ธฐ ๋๋ฌธ์ ์ธ๋ฑ์ค๋ฅผ ํ์ง ์๊ณ  ํ ์ค์บ์ด ๋ฐ์ํ  ์๋ ์๋ค. ๊ผญ ํ์ธํ๊ณ  ์ฌ์ฉ.
- ์ธ๋ฑ์ค ์ปฌ๋ผ ๊ฐ์ ๊ทธ๋๋ก ์ฌ์ฉํด์ผ ํ๋ค. ์๋ฅผ ๋ค๋ฉด ```col > 100```  ์ ์ธ๋ฑ์ค๋ฅผ ํ์ง๋ง ```col*5 > 500``` ์ ์ธ๋ฑ์ค๋ฅผ ํ์ง ์๋๋ค. 

### ๐ง๏ธ ์ธ๋ฑ์ค ์๋ฃ๊ตฌ์กฐ

### โ๏ธ ํด์ ํ์ด๋ธ
- (key, value)๋ก ๋ฐ์ดํฐ๋ฅผ ์ ์ฅ, ๋น ๋ฅธ ๊ฒ์(O(1))
- ๊ทธ๋ฌ๋ ๋ฑํธ์ ๋ํด์๋ง ๊ฒ์์ด ๊ฐ๋ฅํ๊ธฐ ๋๋ฌธ์ ์ ํ์ ์ผ๋ก ์ฌ์ฉ์ด ๊ฐ๋ฅํ์ฌ ์ ์ฌ์ฉํ์ง ์๋๋ค.(Range Scan์ด ๋ถ๊ฐ๋ฅ.)

### โ๏ธ B-Tree
![img.png](img/img_10.png)     
์ถ์ฒ: http://www.btechsmartclass.com/data_structures/b-trees.html

B-Tree๋ ๊ท ํ์๋ ํธ๋ฆฌ์ด๋ฉฐ ์ด์ง ํธ๋ฆฌ์ ๋ฌ๋ฆฌ ํ ๋ธ๋์์ ์ฌ๋ฌ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ง ์ ์๋ค. ํ ๋ธ๋์ ํค์ ์๊ฐ n๊ฐ๋ผ๋ฉด ์์์ n+1๊ฐ๋ฅผ ๊ฐ์ง๊ฒ ๋๋ค.
์ต๋ M๊ฐ์ ์์์ ๊ฐ์ง ์ ์๋ B-Tree๋ฅผ M์ฐจ B-Tree๋ผ๊ณ  ํ๋ค. ๋ธ๋์์ ํค๋ค์ ๋์๊ด๊ณ๋ฅผ ์ด์ฉํ์ฌ ๋ฐ์ดํฐ๋ฅผ ํ์ํ๋ค.

### โ๏ธ B+Tree
#### - InnoDB์ ์ฌ์ฉ๋ B+Tree
![img_1.png](img/img_9.png)      
์ด๋ฏธ์ง ์ถ์ฒ: https://mangkyu.tistory.com/96

- DB์์๋ B-Tree์์ ๋ฆฌํ๋ธ๋์๋ง ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ๋๋ก ๋ณ๊ฒฝํ B+Tree๋ฅผ ์ฃผ๋ก ์ฌ์ฉํ๋ค.
- ๋ชจ๋  ๋ฆฌํ๋ธ๋๋ LinkedList๋ก ์ฐ๊ฒฐ๋์ด ์์ด ์ด๋ฅผ ํตํด ์์ฐจํ์๋ ๊ฐ๋ฅํ๋ค.
- ๋ฆฌํ๋ธ๋๋ฅผ ์ ์ธํ ๋ธ๋์๋ ๋ฐ์ดํฐ๋ฅผ ๋ด์ง ์๊ธฐ ๋๋ฌธ์ ๋ฉ๋ชจ๋ฆฌ๋ฅผ ๋ ํ๋ณดํจ์ผ๋ก์จ ํ ๋ธ๋์ ๋ ๋ง์ ํค๋ฅผ ๋ด์ ์ ์๋ค. ์ด๋ ํธ๋ฆฌ์ ๋์ด๊ฐ
๋ฎ์์ง๋ ํจ๊ณผ๊ฐ ์๊ณ , ๊ฒฐ๊ณผ์ ์ผ๋ก ์ฑ๋ฅ์ ์ํฅ์ ์ค๋ค.
  

### ๐ง ์ B-Tree๋ฅผ ์ฌ์ฉํ๋?
ํ์์๊ฐ์ด O(log n)์ธ ์๊ณ ๋ฆฌ์ฆ์ ๋ง๋ค. ๊ทธ๋ฐ๋ฐ ์ ๋ฐ์ดํฐ๋ฒ ์ด์ค์์๋ B-Tree๋ฅผ ์ฌ์ฉํ ๊น? ์ผ๋จ ๊ฐ์ฅ ํ์ ์๋๊ฐ ๋น ๋ฅธ ์๋ฃ๊ตฌ์กฐ๋ก๋
ํด์ ํ์ด๋ธ์ด ์๋ค. ํด์ ํ์ด๋ธ์ ํด์ ๊ฐ์ ์ด์ฉํด ์ถฉ๋์ ์ ์ธํ๋ฉด O(1)์ ์๊ฐ ๋ณต์ก๋๋ฅผ ๊ฐ์ง๋ค. ๊ทธ๋ฌ๋ ํด์ ํ์ด๋ธ์ ๊ฐ์ด ์ ๋ ฌ๋์ด ์์ง ์๊ธฐ ๋๋ฌธ์
๋ฐ์ดํฐ๋ฒ ์ด์ค์์ ์์ฃผ ๋ค๋ฃจ๊ฒ ๋๋ ๋ถ๋ฑํธ ์ฐ์ฐ(<,>= ..)๋ฑ์ ์ฌ์ฉํ  ์ ์๋ค.

๊ทธ๋ ๋ค๋ฉด ๋ค๋ฅธ O(log n)์ด ๊ฑธ๋ฆฌ๋ ์๋ฃ๊ตฌ์กฐ๋ ์๋๋ฐ ์ B-Tree๋ฅผ ์ฌ์ฉํ์๊น. ์๋ฅผ ๋ค๋ฉด B-Tree์ ๋๊ฐ์ ๋ฐธ๋ฐ์ค ํธ๋ฆฌ์ธ ๋ ๋-๋ธ๋ ํธ๋ฆฌ๊ฐ ์๋ค.
์ด์ ๋ฅผ ์๊ธฐ ์ํด์๋ ๋ ์๋ฃ๊ตฌ์กฐ์ ์ฐจ์ด์ ์ง์คํ  ํ์๊ฐ ์๋ค. ๊ฐ์ฅ ํฐ ์ฐจ์ด๋ผ๋ฉด B-Tree์์๋ ํ ๋ธ๋์ ์ฌ๋ฌ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ  ์ ์๋ค๋ ๊ฒ์ด๋ค.

#### ๐ค ๊ทธ๊ฒ ๊ทธ๋ ๊ฒ ํฐ ์ฅ์ ์ธ๊ฐ?
๊ทธ๋ ๊ฒ ํฐ ์ฅ์ ์ด๋ค. ํ ๋ธ๋์ ์ฌ๋ฌ๊ฐ์ ๋ฐ์ดํฐ๊ฐ ์กด์ฌํ๋ค๋ ๊ฒ์ ํ๋ฒ์ ์ ๊ทผ์ผ๋ก ์ฌ๋ฌ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ ํ์ํ  ์ ์๋ค๋ ๋ง์ด๋ค.
์ฆ ํฌ์ธํฐ๊ฐ์ผ๋ก ์ ๊ทผํ  ํ์์์ด ๋ง์น ๋ฐฐ์ด์ฒ๋ผ ๋ค์ ์กฐ์๋ก ์ธ๋ฑ์ค์ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค. ๋๋ฌธ์ ํฌ์ธํฐ๋ก ์ ๊ทผํ๋ฉฐ ์ค์  ๋ฉ๋ชจ๋ฆฌ์์ ์ฃผ์๋ฅผ ์ฐ์ฐํ๋ ๊ณผ์ ์ด ๋น ์ง๊ฒ ๋๊ณ ,
๊ฒฐ๊ณผ์ ์ผ๋ก ๋ ๋-๋ธ๋ ํธ๋ฆฌ๋ณด๋ค ํ์ ์๋๊ฐ ๋น ๋ฅด๋ค๊ณ  ๋ณผ ์ ์๋ค.

๊ฒฐ๋ก ์ ์ผ๋ก ํญ์ ์ ๋ ฌ๋ ์ํ๋ฅผ ์ ์งํ๊ธฐ ๋๋ฌธ์ ๋ถ๋ฑํธ ์ฐ์ฐ๋ ๊ฐ๋ฅํ๋ฉฐ, ์ฐธ์กฐ ํฌ์ธํฐ๊ฐ ์ ์ด ๋น ๋ฅธ ๋ฉ๋ชจ๋ฆฌ ์ ๊ทผ์ด ๊ฐ๋ฅํ๋ค.
๋ง์ง๋ง์ผ๋ก ๋ฐ์ดํฐ์ ์ ์ฅ,์์ , ์ญ์ ์์๋ O(lon n)์ ์๊ฐ๋ณต์ก๋๋ฅผ ๊ฐ์ง๊ธฐ ๋๋ฌธ์ B-Tree๊ฐ ๋ฐ์ดํฐ๋ฒ ์ด์ค์ฉ ์ธ๋ฑ์ค๋ก ๊ฐ์ฅ ์ ํฉํ ์๋ฃ๊ตฌ์กฐ๋ผ๊ณ  ํ  ์ ์๊ฒ ๋ค.

## ๐ง Clustered Key์ Non Clustered Key
![img.png](img/img_11.png)      
์ถ์ฒ : https://jojoldu.tistory.com/476     

- Clustered Key๋ PK ๋๋ PK์์ ๊ฒฝ์ฐ ์ ๋ํฌํค, ๋๋ค ์์ ๊ฒฝ์ฐ์๋ 6Byte๋ก ์์ฑ๋๋ Hidden key(rowId)๋ฅผ ๋งํ๋ค.

Clustered Key๋ง์ด ์ค์  ํ์ด๋ธ์ Row ์์น๋ฅผ ์๊ณ  ์๋ค. ๋๋ฌธ์  ์ด๋ฅผ ์๊ธฐ ์ํด Non Clustered Key์๋ ์ธ๋ฑ์ค ์ปฌ๋ผ์ ๊ฐ๋ค๊ณผ ํจ๊ป Clustered key๊ฐ ํญ์ ํฌํจ๋๋ค.
์ธ๋ฑ์ค ํ์ ์กฐ๊ฑด์ ๋ถํฉํ๋๋ผ๋ select์ ์ธ๋ฑ์ค์ ํฌํจ๋ ๋ฐ์ดํฐ ์ธ์ ๋ค๋ฅธ ์ปฌ๋ผ ๊ฐ์ด ํ์ํ๋ค๋ฉด Clustered key๊ฐ์ผ๋ก ์ค์  ๋ฐ์ดํฐ ๋ธ๋ก์ ์ฐพ๋ ๊ณผ์ ์ด ํ์ํ๋ค.



## ๐ง ์ปค๋ฒ๋ง ์ธ๋ฑ์ค

์ฟผ๋ฆฌ ์คํ๊ณํ์ ๋ณผ ๋ Extra ํ๋์์ Using Index๋ผ๋ ๊ฒ์ ๋ณธ ์ ์ด ์์ ๊ฒ์ด๋ค. ์ด๊ฒ์ ์ปค๋ฒ๋ง ์ธ๋ฑ์ค๊ฐ ์ฌ์ฉ๋์๋ค๋ ๊ฒ์ ์๋ฏธํ๋ค.
์ปค๋ฒ๋ง ์ธ๋ฑ์ค๋ ์ค์  ๋ฐ์ดํฐ์ ์ ๊ทผ ํ์ ์์ด ์ธ๋ฑ์ค์ ์๋ ์ปฌ๋ผ๊ฐ ๋ค๋ก๋ง ์ฟผ๋ฆฌ๋ฅผ ์์ฑํ๋ ๊ฒ์ ๋ปํ๋ค.    
์ฆ, ์ธ๋ฑ์ค๋ฅผ ๊ตฌ์ฑํ๋ ์ปฌ๋ผ์ด select, order by, group by ์ ์ ์๋ ๋ชจ๋  ์ปฌ๋ผ์ ํฌํจํ๋ค.


### โ๏ธ GROUP BY ์์์ ์ปค๋ฒ๋ง
GROUP BY ์ ์์๋ ๋ช์๋ ์ปฌ๋ผ์ ์์์ ์ธ๋ฑ์ค์ ์ปฌ๋ผ ์์๊ฐ ๋ฐ๋์ ๊ฐ์์ผ ํ๋ค.
์๋ฅผ ๋ค์ด ์ธ๋ฑ์ค์ ์ปฌ๋ผ์ด a, b, c ๋ผ๋ฉด
- group by a
- group by a, b
- group by a, b, c
๋ง์ด ๊ฐ๋ฅํ๋ค.
  
ํ์ง๋ง WHERE ์ ์ ๊ฐ์ด ์ฌ์ฉํ๊ณ , WHERE ์  ์์ ๋๋ฑ ๋น๊ต๋ฅผ ์ฌ์ฉํ๋ค๋ฉด WHERE์ ์ ๋ํ๋๋ ์ปฌ๋ผ์ GROUP BY ์ ์ ์์ด๋ ์ธ๋ฑ์ค๊ฐ ์ ์ฉ๋๋ค.
```sql
WHERE a =1
GROUP BY b, c
```
์์ ์กฐ๊ฑด์ ๋ง์ง ์์ GROUP BY ์ ์ด ์ธ๋ฑ์ค๋ฅผ ์ ๋๋ก ํ์ง ๋ชปํ๋ค๋ฉด Using temporary์ Using filesort๊ฐ Extra ํ๋์ ๋ฑ์ฅํ๋ค.
์ธ๋ฑ์ค์ ํค ์์๋๋ก ์ด๋ฃจ์ด์ง์ง ์์ ์์ ํ์ด๋ธ์ ๋ง๋ค๊ณ  ํด๋น ํ์ด๋ธ์์ ์ ๋ ฌ์ด ์ด๋ฃจ์ด์ง๋ค.


### โ๏ธ ORDER BY ์์์ ์ปค๋ฒ๋ง
- ๋์ฒด์ ์ผ๋ก GROUP BY ์์์ ๋น์ทํ๋ค.
- ์ธ๋ฑ์ค ์ปฌ๋ผ ์ ์ฒด์ ASC ๋๋ DESC ์ค์บ๋ง ๊ฐ๋ฅํ๋ค.
- ORDER BY ์ GROUP BY ๋ฅผ ๋์์ ์ฌ์ฉํ๋ค๋ฉด ๋๋ค ์กฐ๊ฑด์ ๋ง์กฑํด์ผ ์ธ๋ฑ์ค๋ฅผ ํ๋ค.

<br><br><br>

> https://jojoldu.tistory.com/243         
> https://helloinyong.tistory.com/296       
> https://jojoldu.tistory.com/476      