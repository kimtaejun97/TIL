# ๐ป ์ด์์ฒด์ 
****
> ์์คํ์ ์์๊ณผ ๋์์ ๊ด๋ฆฌํ๋ ์์คํ ์ํํธ์จ์ด.

- ํ๋ก์ธ์ค ๊ด๋ฆฌ: ์์ฑ๊ณผ ์ค๋น, ์คํ๊ณผ ๋๊ธฐ, ์ข๋ฃ ๋ฑ ์ฃผ๊ธฐ๋ฅผ ๊ด๋ฆฌํ๊ณ  Deadlock ๋ฑ์ ๋์ฒ.
- ๋ฉ๋ชจ๋ฆฌ ๊ด๋ฆฌ: ๋ฉ๋ชจ๋ฆฌ์ ํ ๋น๊ณผ ํด์ .
- ์์ ์ค์ผ์ค๋ง: ๊ฐ ํ๋ก์ธ์ค์ CPU ํ ๋น ์๊ฐ ๊ด๋ฆฌ.
- ํ์ผ ์์คํ ๊ด๋ฆฌ: ํ์ผ, ํด๋์ ๊ณ์ธต๊ณผ ์์ฑ ์ญ์ , ์ฝ๊ธฐ ์ฐ๊ธฐ.
- ์ปค๋ ๋ชจ๋ / ์ ์  ๋ชจ๋ ๊ด๋ฆฌ.(๋์ผ ๋ชจ๋): Mode bit๋ฅผ ์ด์ฉํ ๋ชจ๋ ๋ณ๊ฒฝ, ์ ๊ทผ์ ๊ด๋ฆฌํ์ฌ OS๋ฅด ๋ณดํธํ๋ค.
- ๋์คํฌ ๊ด๋ฆฌ: ๋์คํฌ ๊ณต๊ฐ ๊ด๋ฆฌ์ ์ฝ๊ธฐ, ์ฐ๊ธฐ. ๋ค๋ฅธ ๊ณ์ธต๊ณผ์ ๋ฐ์ดํฐ ์ผ๊ด์ฑ ์ ์ง.

# ๐ ํ๋ก์ธ์ค
- ํ๋ก์ธ์ค๋ ์ต์ 1๊ฐ ์ด์์ ์ฐ๋ ๋๋ฅผ ๊ฐ์ง๋ค.
- ํ๋ก์ธ์ค๋ Code, Data, Heap, Stack ์์ญ์ ๊ฐ์ง๋ฉฐ, Stack ์์ญ์ ์ฐ๋ ๋๋ง๋ค ์์ฑ๋๊ณ , ๋๋จธ์ง๋ ๊ณต์ ๋๋ค.
> - Code : ์ฝ๋๋ฅผ ๊ตฌ์ฑํ๋ ๋ฉ๋ชจ๋ฆฌ ์์ญ, ํ๋ก๊ทธ๋จ ๋ช๋ น ๋ฑ.
> - Data : static ๋ณ์, ์ ์ญ๋ณ์, ๋ฐฐ์ด ๋ฑ ์ด๊ธฐํ๋ ๋ฐ์ดํฐ๊ฐ ์ ์ฅ๋๋ค.
> - Heap : ๋์  ํ ๋น์ ์ ์ฅ๋๋ ์ฅ์, new Operation ๋ฑ.
> - Stack : ์ง์ญ๋ณ์, ๋งค๊ฐ๋ณ์, ๋ฐํ ๊ฐ์ ์ ์ฅ. ์์ ๋ฉ๋ชจ๋ฆฌ ์์ญ.
> Active Record๊ฐ ์กด์ฌํ์ฌ ํธ์ถ๋ ํ๋ก์์ ์ ์คํ์ ํ์ํ ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ๋ค.

## ๐ง ํ๋ก์ธ์ค ์ํ ์ฃผ๊ธฐ
![img_1.png](img/img_1.png)
- new : ํ๋ก์ธ์ค ์์ฑ
> - Admitted : ํ๋ก์ธ์ค ์์ฑ์ด ๊ฐ๋ฅํ์ฌ ์น์ธ.
- Ready : ํ๋ก์ธ์ค ์คํ์ด ๊ฐ๋ฅํด์ ธ ์ฐจ๋ก๋ฅผ ๊ธฐ๋ค๋ฆผ.
> - Scheduler Dispatch : Ready์ ์๋ ํ๋ก์ธ์ค ์ค ํ๋๋ฅผ ์คํ.
- Running : ํ์ฌ ์คํ์ค์ธ ํ๋ก์ธ์ค
> - Interrupt : ์์ธ, ์์ถ๋ ฅ ๋ฑ์ด ๋ฐ์ํ์ฌ ๋ค๋ฅธ ์์์ ๋จผ์  ์ฒ๋ฆฌ.   
> - IO | Event Wait : ์คํ์ค์ธ ํ๋ก์ธ์ค๊ฐ ์์ถ๋ ฅ์ด๋ ์ด๋ฒคํธ๋ฅผ ๋๊ธฐ.
- Waiting : ๋ฐ์ํ ์์ถ๋ ฅ์ด๋ ์ด๋ฒคํธ๊ฐ ๋ชจ๋ ๋๋ ๋๊น์ง ๋๊ธฐํ๋ ์ํ.
> - IO | Event Completion : ์์ถ๋ ฅ์ด๋ ์ด๋ฒคํธ๊ฐ ๋๋ ๋ค์ Ready ์ํ๋ก ์ ํ.

## ๐ง ํ๋ก์ธ์ค ์ค์ผ์ค๋ง
### โ๏ธ Queue
- Jop Queue: Disk ์์ Ready ์ํ๋ก ๊ฐ๊ธฐ์ํด ํ๋ก์ธ์ค๋ค์ด ๋๊ธฐํ๋ ํ.
- Ready Queue: Ready ์ํ์ ํ๋ก์ธ์ค๋ค์ด CPU๋ฅผ ํ ๋น ๋ฐ๊ธฐ ์ํด ๋๊ธฐํ๋ ํ.
- Device Queue: Device Controller์ ์กด์ฌ, I/O๋ฅผ ์ด์ฉํ๊ธฐ ์ํด ๋๊ธฐํ๋ ํ.

### โ๏ธ Scheduler
- Long Term Scheduler
  > JobQueue -> ReadyQueue๋ก ์ด๋์ํค๋ ์์. ๊ธด ํ์ ๋๊ณ  ์คํ, ๋ฉ๋ชจ๋ฆฌ์ ์๋ ์์์ ์กฐ์ ํ๋ค.
- Short Term Scheduler
  > ํ๋ก์ธ์ค์ CPU๋ฅผ ํ ๋นํ๋ค ์์ฃผ ์คํ๋๋ฉฐ ์คํ์๋๊ฐ ๋นจ๋ผ์ผํ๋ค.
- Medium Term Scheduler
  > ๋๋ฌด ๋ง์ ํ๋ก์ธ์ค์ ์คํ์ผ๋ก ๋ฉ๋ชจ๋ฆฌ๊ฐ ๋ถ์กฑํด ์ง๋ ์ผ๋ถ ํ๋ก์ธ์ค๋ฅผ ๋ค์ ๋์คํฌ๋ก ๋ด๋ ค๋ณด๋ด๊ฑฐ๋, ๋ค์ ์ฌ์ ๊ฐ ์๊ธฐ๋ฉด ๋ฉ๋ชจ๋ฆฌ์ ์ฌ๋ฆฌ๋ ์์์ ํ๋ค.(Swapping)

โ๏ธ I/O Bound, CPU Bound Process: I/O ์ค์ฌ, CPU ์ค์ฌ์ ํ๋ก์ธ์ค์ด๋ค. ์์คํ ์ฑ๋ฅ์ ์ํด์๋ ๋์คํฌ์์ ๋ ํ์์ ํ๋ก์ธ์ค๋ฅผ ์ ์ ํ๊ฒ ์ ํํ์ฌ ๊ฐ์ ธ์์ผ ํ๋ค.
> CPU์ฒ๋ฆฌ ๊ณผ์ด -> I/O Bound ์คํ

- ์ฌ๋ฌ๊ฐ์ ํ๋ก์ธ์ค๋ฅผ ๋ณ๋ ฌ์ ์ผ๋ก ์ฒ๋ฆฌํ๋ค. 
- Context Switching ์ด ๋ฐ์ํ๊ธฐ ๋๋ฌธ์ ์ค๋ฒํค๋๊ฐ ์๋ค.
    - ๋์์ค์ธ ํ๋ก์ธ์ค์ ์ํ๋ฅผ ์ ์ฅํ๊ณ , ์ด์ ์ ๋ณด๊ดํ ํ๋ก์ธ์ค์ ์ํ๋ฅผ ๋ณต์ํ๋ ๊ณผ์ .
    - ์ธํฐ๋ฝํธ์ ๋ฐ์, CPU ์ฌ์ฉ์๊ฐ ์๋ชจ, ์์ถ๋ ฅ ๋๊ธฐ ๋ฑ.
    - ์ฆ, ํ๋ก์ธ์ค์ ์ํ ๋ณ๊ฒฝ์ ๋ฐ์


### โ๏ธ PCB(Process Control Block)
ํ๋ก์ธ์ค ์ ์ด๋ธ๋ก, ํน์  ํ๋ก์ธ์ค์ ๋ํ ์ค์ํ ์ ๋ณด๋ฅผ ์ ์ฅํ๊ณ  ์๋ ์ด์์ฒด์ ์ ์๋ฃ๊ตฌ์กฐ์ด๋ค.
CPU๋ฅผ ํ ๋น๋ฐ์ ์์์ ์ฒ๋ฆฌํ๋ค๊ฐ Context Switch๊ฐ ๋ฐ์ํ๋ฉด ์งํํ๋ ์์์ ์ ์ฅํด์ผํ๋๋ฐ ์ด๋ ์์์ ์งํ ์ํฉ์
PCB์ ์ ์ฅํ๊ฒ ๋๋ค.

Linked List ๋ฐฉ์์ผ๋ก, ํ๋ก์ธ์ค๊ฐ ์์ฑ๋๋ฉด ์ถ๊ฐ๋๊ณ , ์๋ฃ๋๋ฉด ์ญ์ ๋๋ค.

- ํ๋ก์ธ์ค ์๋ณ์(PID)
- ํ๋ก์ธ์ค ์ํ(new, ready, running, waiting ๋ฑ)
- ์ค์ผ์ฅด๋ง ์ ๋ณด: ์ฐ์ ์์, ์ค์ผ์ค ํ์ ๋ํ ํฌ์ธํฐ
- ๋ฉ๋ชจ๋ฆฌ ๊ด๋ฆฌ ์ ๋ณด: ํ์ด์ง, ์ธ๊ทธ๋จผํธ ํ์ด๋ธ
- ์์ถ๋ ฅ ์ํ ์ ๋ณด: ํ๋ก์ธ์ค์ ํ ๋น๋ ์์ธ๋ ฅ ์ฅ์น
- CPU Registers
- ์ด์นด์ดํ ์ ๋ณด: ๋ฉ๋ชจ๋ฆฌ, CPU ์ฌ์ฉ๋ ๋ฑ.


## ๐ง ๋ฉํฐ ํ๋ก์ธ์ฑ
- ์ฌ๋ฌ๊ฐ์ ํ๋ก์ธ์ค๊ฐ ๋ฒ์ค, ๋ฉ๋ชจ๋ฆฌ๋ฅผ ๊ณต์ ํ๋ฉฐ ํ๋ ฅ.
> - ํ๋ก์ธ์์ ์์ ์ฆ๊ฐ๋ก ์ฒ๋ฆฌ ์๋๊ฐ ํฅ์๋๋ค. ํ์ง๋ง ์ค๋ฒํค๋๊ฐ ์กด์ฌํ๊ธฐ ๋๋ฌธ์ ์ ํ์ผ๋ก ์ฆ๊ฐํ์ง ์๋๋ค.
> - ํ๋์ ํ๋ก์ธ์ค๊ฐ ์๋์ ๋ฉ์ถ๋๋ผ๋ ๋ค๋ฅธ ํ๋ก์ธ์ค๊ฐ ์ฒ๋ฆฌํ  ์ ์์ผ๋ฏ๋ก ์์ ์ ์ด๋ค.
- ### 1. ๋น๋์นญ ๋ฉํฐ ํ๋ก์ธ์ฑ
  - ๊ฐ ํ๋ก์ธ์ค๋ ์์ ์ด ๋งก์ ์ญํ ๋ง์ ํ๋ค. 
- ### 2. ๋์นญ ๋ฉํฐ ํ๋ก์ธ์ฑ
  - ํ๋ก์ธ์ค๋ค์ ๋ฏธ๋ฆฌ ์ ์๋ ์ผ ์์ด ๋น์ด์๋ ํ๋ก์ธ์ค๊ฐ ์๋ก์ด ์์์ ์ฒ๋ฆฌํ๊ฒ ๋๋ค.

## ๐ง ํ๋ก์ธ์ค๊ฐ ํต์  IPC(Inter-Process Communication)
ํ๋ก์ธ์ค๋ ๋๋ฆฝ์ ์ผ๋ก ์คํ๋์ด ์๋ก์๊ฒ ์ํฅ์ ๋ฐ์ง ์๋๋ค.
ํ๋ก์ธ์ค๋ ์ปค๋์ด ์ ๊ณตํ๋ IPC ์ค๋น๋ฅผ ์ด์ฉํ์ฌ ์๋ก ํต์ ํ๋ค.

### โ๏ธ 1. PIPE
- #### ์ต๋ช PIPE
  - ํต์ ํ  ํ๋ก์ธ์ค๋ฅผ ๋ชํํ๊ฒ ์ ๋ ์ฌ์ฉ.(๋ถ๋ชจ-์์)
  - ๋จ๋ฐฉํฅ ํต์ (ํ์ชฝ์ ์ฝ๊ธฐ, ํ์ชฝ์ ์ฐ๊ธฐ.)
  - ์๋ฐฉํฅ์ผ๋ก ํต์ ํ๊ณ  ์ถ๋ค๋ฉด 2๊ฐ์ ํ์ดํ๊ฐ ํ์ํ๋ค. ๊ตฌํ์ด ๊ฐ๋จํ์ง๋ง ์๋ฐฉํฅ ํต์ ์ ํ๊ธฐ ์ํด์๋ ๊ตฌํ์ด ๋ณต์กํด์ง๋ค.
  
- #### Named PIPE
  - ํต์ ํ  ํ๋ก์ธ์ค๋ฅผ ์์ง ๋ชปํ  ๋ ์ฌ์ฉํ๋ค. ์ต๋ช ํ์ดํ์ ํ์ฅ์ผ๋ก, ๋ฌด๊ดํ ํ๋ก์ธ์ค์๋ ํต์ ์ด ๊ฐ๋ฅ.

### โ๏ธ 2. Message Passing
- #### ์ง์  ํต์ 
  - ํ๋ก์ธ์ค๊ฐ ์ง์ ์ ์ผ๋ก ๋งํฌ๊ฐ ์ฐ๊ฒฐ๋๋ค.
  - send: p->Q ๋ก ๋ฉ์์ง๋ฅผ ๋ณด๋ธ๋ค.
  - receive: P๋ก๋ถํฐ ๋ฉ์์ง๋ฅผ ๋ฐ๋๋ค.

- #### ๊ฐ์  ํต์ : Message Queue
  - ์์ถ๋ ฅ ๋ฐฉ์์ ํ์ดํ์ ๋์ผํ์ง๋ง, ํ์ดํ์ ๋ฌ๋ฆฌ ๋ฐ์ดํฐ์ ํ๋ฆ์ด ์๋ ๋ฉ๋ชจ๋ฆฌ ๊ณต๊ฐ(mail box, ๊ณ ์  id๊ฐ ์กด์ฌ.)
  - ์ฌ๋ฌ ํ๋ก์ธ์ค์์ ๋ฐ์ดํฐ๋ฅผ ๋ค๋ฃฐ ์ ์๊ณ , mail box ์์ฒด๋ฅผ ์ฌ๋ฌ๊ฐ ์์ฑํ์ฌ ์ฐ๊ฒฐํ  ์ ์๋ค.

### โ๏ธ 3. ๊ณต์  ๋ฉ๋ชจ๋ฆฌ
ํต์ ์ ์ด์ฉํ ์ค๋น๊ฐ ์๋ ๋ง ๊ทธ๋๋ก ์์์ ๊ณต์ ์ด๋ค. ํ๋ก์ธ์ค๊ฐ ๋ฉ๋ชจ๋ฆฌ ์์ญ์ ๊ณต์ ํ์ฌ ์ฌ์ฉํ  ์ ์๋๋ก ํ๋ค. ๊ณง๋ฐ๋ก ์ ๊ทผ์ด ๊ฐ๋ฅํ๊ธฐ ๋๋ฌธ์ ๊ฐ์ฅ ๋น ๋ฆ.
- ํ๋ก์ธ์ค๊ฐ ๊ณต์  ๋ฉ๋ชจ๋ฆฌ ํ ๋น์ ์ปค๋์ ์์ฒญ.
- ์ปค๋์ ๋ฉ๋ชจ๋ฆฌ ๊ณต๊ฐ์ ํ ๋น. ๋ชจ๋  ํ๋ก์ธ์ค๋ ํด๋น ๋ฉ๋ชจ๋ฆฌ ์์ญ์ ์ ๊ทผ ๊ฐ๋ฅ.
- ๊ณต์  ์์์ ์ ํํ๊ธฐ ๋๋ฌธ์ ์์ฑ์ - ์๋น์ ๋ฌธ์ ๊ฐ ๋ฐ์ํ๋ค.(์์์ด ์๋์ง ์๋์ง ํ์ธํ๋ฉฐ wait, notify๋ฅผ ์ด์ฉํ์ฌ ์๋ ค ํด๊ฒฐ.)

### โ๏ธ 4. ๋ฉ๋ชจ๋ฆฌ ๋งต
- ๊ณต์  ๋ฉ๋ชจ๋ฆฌ์ ๊ฐ์ด ๋ฉ๋ชจ๋ฆฌ๋ฅผ ๊ณต์ ํ๋ค.
  ์ด๋ฆฐ **ํ์ผ**์ ๋ฉ๋ชจ๋ฆฌ์ ๋งตํ ์์ผ ๊ณต์ , ๋์ฉ๋ ๋ฐ์ดํฐ๋ฅผ ๊ณต์ ํ  ๋ ์ฌ์ฉํ๋ค.

### โ๏ธ 5. Socket
๋คํธ์ํฌ ์์ผ ํต์ ์ ์ด์ฉํ ๋ฐ์ดํฐ ๊ณต์ ์ด๋ค.
- IP + Port
- ํด๋ผ์ด์ธํธ-์๋ฒ ํต์  ๊ตฌ์กฐ.

### โ๏ธ 6. RPC(Remote Procedure Call)
- Data ๋ ๊ธฐ๊ณ์ ๋ฐ๋ผ *Big Endian, *Little Endian ์ผ๋ก ๋๋๋๋ฐ ์ด๋ฅผ ๊ธฐ๊ณ์ ๋๋ฆฝ์ ์ผ๋ก ๋ณํํ๊ธฐ ์ Marshaling ๊ธฐ๋ฒ์ ์ฌ์ฉํ๊ณ ,
  ๋ค๋ฅธ ์ปดํจํฐ์ ํฌํธ๋ฒํธ์ IP๋ฅผ ์ด์ฉํ์ฌ ๋ฐ์ดํฐ๋ฅผ ์ ์กํ๋ค. ์ดํ ๊ฒฐ๊ณผ๋ฅผ ๋ค์ ์ ๋ฌ ๋ฐ๊ฒ ๋๋ค.

#### ๐ก Big,Little Endian
    - ์ปดํจํฐ๊ฐ ๋ฐ์ดํฐ๋ฅผ ์ฝ๊ณ  ์ธ๋์ ๋ฐฉ์.
    - 1byte ๋ฅผ ์ต์ ๋จ์๋ก ๋์ด ์ฝ๊ณ , Big Endian ๊ฐ์ ๊ฒฝ์ฐ๋ ํฐ ๋จ์๊ฐ ์์ ๋นํธ์, Little Endian์ ์์ ๋จ์๊ฐ ์์ ๋นํธ์ ์ฐ์ฌ์ง๋ค.
      - 16์ง์ 0x123456์ ์๋ก ๋ค๋ฉด Big Endian์ 12 34 56 ์ผ๋ก ํ ๋น, Little Endian ์ 56 34 12๋ก ํ ๋น ๋๋ค.


#### ๐ IPC ํต์ ์์ ๋๊ธฐํ, ๊ฐ์ข ๋ฌธ์  ๋ฐ์์ ํด๊ฒฐํ๊ธฐ ์ํด ์ธ๋งํฌ, ๋ฎคํ์ค ๋ฑ์ ๋ฐฉ๋ฒ์ ์ฌ์ฉํ๋ค.

  
# ๐ Thread 
- CPU ์์ ์ฌ์ฉํ๋ ๊ธฐ๋ณธ์ ์ธ ์คํ ๋จ์.
- ๊ฐ๋ณ์ ์ธ Id, PC, Register Set, Stack ์ ๊ฐ์ง๊ณ  ํ๋ก์ธ์ค์ code, heap, data ์์ญ์ ๊ณต์ ํ๋ค.
- stack ์๋ ํจ์ ์ธ์,๋ณ์, ๋ฆฌํด ์ฃผ์ ๋ฑ ์คํํ๋ฆ์ ๊ด๋ จ๋ ๋ณ์๊ฐ ์ ์ฅ๋๋ค.

### โ๏ธ ๋ฉํฐ ์ฐ๋ ๋
- ํ ํ๋ก์ธ์ค์์ ๋ค์์ ์์์ ๋์์ ์ฒ๋ฆฌ.
  - ์์ ๊ณต์ : ์์์ ๊ณต์ ํ๊ธฐ ๋๋ฌธ์ ๋ฉ๋ชจ๋ฆฌ๋ฅผ ํจ์จ์ ์ผ๋ก ์ฌ์ฉ๊ฐ๋ฅํ๋ฉฐ, ๋ฐ์ดํฐ ๊ตํ์ด ์ฝ๋ค.
  - ๊ฒฝ์ ์ : ์ฐ๋ ๋๊ฐ Context Switching์ ํ๋ก์ธ์ค๊ฐ์ ์ค์์น๋ณด๋ค ์ ๋ ดํ๋ฉฐ ๋น ๋ฅด๋ค.(TLB ์บ์ ๋ฉ๋ชจ๋ฆฌ๋ฅผ ๋น์ธ ํ์๊ฐ ์์.)
  - ๋ฐ์์ฑ ํฅ์: ๋จ์์๊ฐ๋น ์ฒ๋ฆฌ๋์ด ์ฆ๊ฐํ๋ค.
  - ๊ณต์  ์์์ ์ฌ์ฉํ๊ธฐ ๋๋ฌธ์ ๋๊ธฐํ ์ฒ๋ฆฌ ๋ฑ ๋ฐ์ํ  ์ ์๋ ๋ฌธ์ ๋ฅผ ๊ณ ๋ คํด์ผ ํ๋ค. Race Condition ๋ฑ์ ์ ์.
  
### โ๏ธ User, Kernel Thread
- User, Kernel ๋ ๋ฒจ์์ ์คํ๋๋ ์ฐ๋ ๋๋ก, User Thread๋ Kernel Thread์ ์ฐ๊ฒฐ๋์ด์ผ ์ฌ์ฉํ  ์ ์๋ค.
- 1:1 ์ฐ๊ฒฐ: ํ ๊ฐ์ ์ ์  ์ฐ๋ ๋๊ฐ ์์ฑ๋  ๋ ํ๊ฐ์ ์ปค๋ ์ฐ๋ ๋๊ฐ ์์ฑ๋์ด์ผ ํ๊ธฐ ๋๋ฌธ์ ๋งค์ฐ ๋ถ๋ด์ด ํฐ ๋ฐฉ๋ฒ. ์ ์ฌ์ฉ๋์ง ์๋๋ค.
- N:1 ์ฐ๊ฒฐ: ์ฌ๋ฌ๊ฐ์ ์ ์  ์ฐ๋ ๋๊ฐ ํ ๊ฐ์ ์ปค๋ ์ฐ๋ ๋์ ์ฐ๊ฒฐ ๋๋ค.
  > ์ ์  ์ฐ๋ ๋๊ฐ Blocking system call์ ํ๊ฒ ๋๋ฉด ์ปค๋ ์ฐ๋ ๋๊ฐ Block ๋์ด ๋ค๋ฅธ ์ ์  ์ฐ๋ ๋ ๋ํ ์คํ๋  ์ ์๊ฒ ๋๋ค.(๋ณ๋ ฌ์ฑ ๋์จ)
- N:M ์ฐ๊ฒฐ(N>=M): ๋ฏธ๋ฆฌ ์ปค๋ ์ฐ๋ ๋์ ์ซ์๋ฅผ ์ ํด๋๊ณ  ์ฌ์ฉํ๋ค. ๋ณ๋ ฌ์ฑ์ด ๊ฐ์ ๋๋ค.


## ๐ค ๋ฉํฐ ํ๋ก์ธ์ค vs ๋ฉํฐ ์ฐ๋ ๋
- ํ๋ก์ธ์ค๋ ๋ฉ๋ชจ๋ฆฌ ์์์ ์คํ์ค์ธ ํ๋ก๊ทธ๋จ์ ๋งํ๊ณ , ์ฐ๋ ๋๋ ํ๋ก์ธ์ค ์์์ ์คํ๋๋ ํ๋ฆ์ ๊ธฐ๋ณธ ๋จ์์ด๋ค.
- ํ๋ก์ธ์ค๋ ๊ณ ์ ์ ๋ฉ๋ชจ๋ฆฌ ๊ณต๊ฐ์ ๊ฐ์ง๋ค.(heap, stack, code, data)   
  ์ฐ๋ ๋๋ ์ด์ค stack ๋ง์ ๊ฐ์ ๊ฐ์ง๊ณ  ๋๋จธ์ง ์์ญ์ ๊ณต์ ํ๊ฒ ๋๋ค.

***

- **์์ฑ ์ธก๋ฉด:**  ํ๋ก์ธ์ค๋ฅผ ์์ฑํ ๋๋ *์์คํ ์ฝ์ด ๋ฐ์ํ๊ธฐ ๋๋ฌธ์ ์ฐ๋ ๋๋ฅผ ์ด์ฉํ๋ฉด ์ด๋ฅผ ์ค์ฌ ์์์ ํจ์จ์ ์ผ๋ก ์ด์ฉํ  ์ ์๊ฒ๋๋ค.
- **ํต์  ์ธก๋ฉด:** ํ๋ก์ธ์ค๋ ํ๋ก์ธ์ค๊ฐ์ ํต์  *IPC ๋ฅผ ์ด์ฉํ๊ธฐ ๋๋ฌธ์ ์์์ ๊ณต์ ํ๋ ์ฐ๋ ๋ ๋ณด๋ค ํต์  ๋น์ฉ์ด ํฌ๋ค. ๊ทธ๋ฌ๋ ์ฐ๋ ๋๋ ์์์ ๊ณต์ ํ๊ธฐ ๋๋ฌธ์ ํต์ ์ ์ํ ์ถ๊ฐ์ ์ธ ์์์ด ํ์์๋ค.
  ํ์ง๋ง ์์์ ๊ณต์ ํ๊ธฐ ๋๋ฌธ์ ์๊ธฐ๋ ๋ฌธ์ ์ ๋๋น๊ฐ ํ์ํ๋ค.
- **์ค์์นญ:** ํ๋ก์ธ์ค์์ Context Switch๊ฐ ์คํ๋  ๋์๋ TLB Cache๋ฅผ ๋น์ฐ๊ธฐ ๋๋ฌธ์ ๋ ๋ง์ Cashe miss๊ฐ ๋ฐ์ํ  ์ ์์ง๋ง ๋ฉํฐ ์ฐ๋ ๋๋ ์บ์๋ฅผ ๋น์ฐ์ง ์๊ธฐ ๋๋ฌธ์ ๋ ํจ์จ์ ์ด๋ค.
- **์์ ์ฑ:** ๋ฉํฐ ์ฐ๋ ๋์์๋ ํ ์ฐ๋ ๋๊ฐ ๋น์ ์ ์ข๋ฃํ๊ฒ ๋๋ฉด ๋ค๋ฅธ ์ฐ๋ ๋์๊ฒ ์ํฅ์ ๋ฏธ์น๋ค. ํ๋ก์ธ์ค๋ ๋๋ฆฝ์ ์ด๊ธฐ ๋๋ฌธ์ ์ํฅ์ ์ฃผ์ง ์์ ์์ ํ๋ค.
#### ๐ ์ฑ๋ฅ ๋ฉด์์๋ ๋ฉํฐ ์ฐ๋ ๋๊ฐ ๋ฉํฐ ํ๋ก์ธ์ค ๋ณด๋ค ๋ ์ข์ง๋ง ์์ ์ฑ ๋ฉด์์๋ ๋ฉํฐ ํ๋ก์ธ์ค๊ฐ ๋ ์ข๋ค.

### โ๏ธ System Call
fork(), wait(), exit() ๋ฑ ํ๋ก์ธ์ค์ ์์ฑ๊ณผ ์ ์ด๋ฅผ ์ํ ์์คํ ์ฝ์ด ์กด์ฌํ๋ค.
- fork()
> - ํ๋ก์ธ์ค์ ์์ฑ์ ์ฌ์ฉ๋๋ค. ์ด๋ ์์ฑ๋ ํ๋ก์ธ์ค(child)๋ parent์ ๋์ผํ ๋ณต์ฌ๋ณธ์ ๊ฐ์ง๊ฒ ๋๋ค.
> - child์ parent๋ fork() ๊ฐ์ด ๋ค๋ฅด๊ธฐ ๋๋ฌธ์ ์ด๋ฅผ ์ด์ฉํ์ฌ ๊ตฌ๋ถํ๋ค.(parent๋ child์ PID๊ฐ, child๋ 0)
> - ํ๋ก์ธ์ค๋ฅผ ์์ฑํ  ๋์๋ ์ต์์ ์ฌ์ฉํ์ฌ ์์์ ๊ณต์ ๋ฅผ ์ค์ ํ  ์ ์๋ค.
>   - ์ต์: ๋ชจ๋  ์์์ ๊ณต์ , ์ผ๋ถ ์์์ ๊ณต์ , ๊ณต์ ํ์ง ์์. 
> - ์์ ํ๋ก์ธ์ค๋ ๋ถ๋ชจ ํ๋ก์ธ์ค๊ฐ ์์ด ์คํ๋  ์ ์๋ค.
>   - ๋ถ๋ชจ ํ๋ก์ธ์ค๊ฐ ์ข๋ฃ ๋์์ ๊ฒฝ์ฐ: ์์๋ ์ข๋ฃํ๊ฑฐ๋ ๋ค๋ฅธ ๋ถ๋ชจ์ ์์๋ก ๋ค์ด๊ฐ๋ค.
- wait()
> - child์ ์คํ์ด ๋๋๊ธฐ๋ฅผ ๋ถ๋ชจ๊ฐ ๊ธฐ๋ค๋ฆผ.
> - ๋ง์ฝ ๋ถ๋ชจ๊ฐ ๊ธฐ๋ค๋ฆฌ์ง ์๋๋ค๋ฉด ์์ ํ๋ก์ธ์ค๋ ์ข๋ฃ๋ ํ PCB๋ฅผ ๋ฐ๋ฉํ  ์ ์๊ฒ๋๊ณ , **์ข๋น ํ๋ก์ธ์ค**๊ฐ ๋๋ค.
> ๋ํ, ์ด ์ํ์์ ๋ถ๋ชจ๊ฐ ์ข๋ฃ๋๋ค๋ฉด **๊ณ ์ ํ๋ก์ธ์ค**๊ฐ ๋๋ค.


# ๐ Interrupt
๋ ์ค์ํ ์ผ ๋ฐ์์ผ๋ก ์คํ์ค์ธ ์์์ ์ฆ์ ์ค๋จํ๊ณ , ๋ฐ์๋ ์ํฉ์ ์ฒ๋ฆฌ๋ฅผ ์ฐ์ ์ผ๋ก ํ  ๊ฒ์ CPU์๊ฒ ์๋ฆฌ๋ ๊ฒ.
> ๋ฐ์์๊ธฐ๋ฅผ ์์ธกํ๊ธฐ ํ๋  ๊ฒฝ์ฐ์ ๋น ๋ฅด๊ฒ ๋์ํ  ์ ์๊ฒ ํด์ค๋ค.

- ํด๋ง ๋ฐฉ์ : ์ฌ์ฉ์ ๋ช๋ น์ด๋ฅผ ์ฌ์ฉํด ๊ฐ์ ๊ณ์ ์ฝ์ด ๋ณํ๋ฅผ ๊ฐ์ง.
- ์ธํฐ๋ฝํธ : MCU ์์ฒด๊ฐ ํ๋์จ์ด์ ์ผ๋ก ๋ณํ๋ฅผ ์ฒดํฌ, ๋ณํ๊ฐ ์์ ๊ฒฝ์ฐ์๋ง ๋์.


### โ๏ธ ์ธ๋ถ ์ธํฐ๋ฝํธ
์์ถ๋ ฅ, ํ์ด๋ฐ ์ฅ์น, ์ ์ ๋ฑ์ ์ธ๋ถ์ ์ธ ์์ธ
- **I/O ์ธํฐ๋ฝํธ**
- ๋จ๋ง์์ ๋จ๋ง ์ปจํธ๋กค๋ฌ์ ์ง์ญ ๋ฒํผ๋ก ๋ฐ์ดํฐ๊ฐ ์ฎ๊ฒจ์ง๋ Input, ๋จ๋ง ์ปจํธ๋กค๋ฌ์์ ๋จ๋ง๋ก ๋ฐ์ดํฐ๊ฐ ์ฎ๊ฒจ์ง๋ Output์ด ์๋ค.
- ๋จ๋ง ์ปจํธ๋กค๋ฌ์์๋ I/O ์์์ด ์๋ฃ๋๋ฉด ์ธํฐ๋ฝํธ๋ฅผ ๋ฐ์์์ผ CPU ์๊ฒ ์๋ฆฐ๋ค.
- CPU๋ ์ธํฐ๋ฝํธ๊ฐ ๋ฐ์ํ๋ฉด, ํ๋ ์ผ์ ๋ฉ์ถ๊ณ  ํด๋น ์์์ ์ฒ๋ฆฌํ๋ค.
- I/O์ ์์์ ์ธ์  ๋ฐ์ํ ์ง ๋ชจ๋ฅด๊ณ , ์ธ์  ์๋ฃ๋ ์ง ๋ชจ๋ฅด๊ธฐ ๋๋ฌธ์ CPU๊ฐ ์ด๊ฒ์ ๊ธฐ๋ค๋ฆฌ๋ ๊ฒ์ ๋น ํจ์จ์ ์ด๋ค.
- ๋๋ฌธ์ ์ด๋ฅผ ๋์์ฃผ๋ Interrupt Service routine ๋ผ๋ ์ํํธ์จ์ด๊ฐ ์กด์ฌํ๊ฒ ๋๋ค.
  RAM ์๋ Interrupt Vector๋ผ๋ ์ธํฐ๋ฝํธ ์๋น์ค ๋ฃจํด์ ์ฃผ์๋ฅผ ์ ์ฅํด๋ ํ์ด๋ธ์ด ์กด์ฌํ๊ณ , 
  ์ธํฐ๋ฝํธ๊ฐ ๋ฐ์ํ๋ฉด CPU๋ ๋ฒกํฐ ํ์ด๋ธ์ ๋ณด๊ณ  ํด๋น ์ฃผ์๋ก ์ ํํ๊ฒ ๋๋ค.


### โ๏ธ ๋ด๋ถ ์ธํฐ๋ฝํธ
- **Trap**
- ์๋ชป๋ ๋ช๋ น์ด๋ ๋ฐ์ดํฐ๋ก ์ธํด ๋ฐ์ํ๋ ์ธํฐ๋ฝํธ.
  > (overflow, zero division, Exception ๋ฑ..)
    
### โ๏ธ ์ํํธ์จ์ด ์ธํฐ๋ฝํธ
- ๋ช๋ น์ ์์ฒญ์ ์ํด ๋ฐ์.
- ์ฌ์ฉ์๊ฐ ๋ค๋ฅธ ํ๋ก์ธ์ค๋ฅผ ์คํ ๋ฑ.

์๋์ ์์๋ก ์๋๋๋ค.
```
- ์ธํฐ๋ฝํธ ๋ฐ์
- ๋ณต๊ท์ฃผ์ ์ ์ฅ(PC, ๋ ์ง์คํธ ๋ฑ ์คํ์ ์ ์ฅ.).
- ์ธํฐ๋ฝํธ ๋ฐฑํฐ๋ก ์ ํ.
- ์ธํฐ๋ฝํธ ์ฒ๋ฆฌ.
- ๋ณต๊ท ์ฃผ์ ๋ก๋.
- ๋ณต๊ท ์ฃผ์๋ก ์ ํ.
- ์ด์ ์ ์คํํ๋ ํ๋ก์ธ์ค ์คํ.
```

# ๐ CPU Scheduling
- Short Term Scheduler ์ ์ํด ์คํ๋๋ค.
- ๋ชฉํ
  - ๊ฐ๋ฅํ ๋ง์ ์ผ์ ์ํ.
  - ๋น ๋ฅธ ์๋ต ์๊ฐ, ์ ์ ๋๊ธฐ ์๊ฐ
  - Deadline ๋ด์ ์ํ.

> - Response Time: ์์์ด ์ฒ์ ์คํ๋๊ธฐ ๊น์ง ๊ฑธ๋ฆฐ ์๊ฐ
> - Turn Around Time: ์คํ์๊ฐ + ๋๊ธฐ ์๊ฐ.

- Dispatcher ์ ์ํด ํ๋ก์ธ์ค ํ ๋น์ด ์คํ๋๋ค.    
๋จผ์  process context ๋ฅผ ๊ตํํ๊ณ  Dispatcher ๋ ์ปค๋ ๋ชจ๋์์ ๋์ํ๊ธฐ ๋๋ฌธ์ ๋ค์ User Mode๋ก ๋ณ๊ฒฝํด์ค๋ค.
PC๊ฐ ๊ฐ์ง๊ณ  ์๋ ๋ช๋ น์ด๋ก ๋ถ๊ธฐํ์ฌ ํ๋ก๊ทธ๋จ์ ์คํํ๋ค. 
  > ์์ ๊ฐ์ ๊ณผ์ ์์ ๋ฐ์ํ๋ ์ค๋ฒํค๋๋ฅผ Dispatcher Latency ๋ผ๊ณ  ํ๋ค.
  

## ๐ง CPU Scheduling Algorithm

### โ๏ธ ๋น์ ์ (nonpreemptive) ์ค์ผ์ค๋ง
ํ๋ก์ธ์ค ์ข๋ฃ ๋๋ I/O ๋ฑ์ ์ด๋ฒคํธ๊ฐ ์์๋ ๊น์ง ์คํ์ ๋ณด์ฅํ๋ค.
- #### โ๏ธ FCFS(First come First Served)
  - ๋ง ๊ทธ๋๋ก ํ์ ๋์ฐฉํ ์์๋๋ก ํ ๋น. CPU BurstTime ์ด ๋คํ ๋ ๊น์ง ์คํํ๋ค. 
  - ์คํ์๊ฐ์ด ๊ธด ํ๋ก์ธ์ค๊ฐ ์์ ์ค๋ฉด ํ๊ท  ๋๊ธฐ์๊ฐ์ด ๊ธธ์ด์ง๋ค.
  - ๐ **ํธ์ํจ ํจ๊ณผ(Convoy Effect):** ๋์ฒด์ ์ผ๋ก ์งง์ ํ๋ก์ธ์ค๊ฐ ๊ธด ํ๋ก์ธ์ค ๋ค์ ์ค๊ฒ๋๋ ํ์.
    
- #### โ๏ธ SJF(Shortest Job First)
  - ์คํ์๊ฐ์ด ์งง์ ํ๋ก์ธ์ค ๋ถํฐ ์ํ.
      - ํด๋น ํ๋ก์ธ์ค์ ๊ณผ๊ฑฐ ๊ธฐ๋ก์ผ๋ก ๋ถํฐ ์คํ์๊ฐ์ ์์ธกํ๋ค.
      - E(n+1) = k*T(n) +(1-k)E(n)
        - T(n): ์ค์  ์์ ์๊ฐ, E(n): ์์ธก ์๊ฐ.
  - FCFS ๋ณด๋ค๏ธ ํ๊ท  ๋๊ธฐ์๊ฐ์ด ๊ฐ์
  - ๐ **Starvation(๊ธฐ์ ํ์)** ์ด ๋ฐ์ํ  ์ ์๋ค.
  
- #### โ๏ธ HRN(Highest Response-ratio Next)
  - ์ฐ์ ์์๋ฅผ ๊ณ์ฐํ์ฌ ์ ์  ๋ถํ๋ฑ์ ํด๊ฒฐ(SJF ๋ณด์)
  - ์ฐ์ ์์ = (๋๊ธฐ์๊ฐ + ์คํ์๊ฐ) / ์คํ์๊ฐ
  - ๋๊ธฐ์๊ฐ์ด ๊ธธ์ด์ง๋ฉด ์ฐ์ ์์๊ฐ ๋์์ง.


### โ๏ธ ์ ์ (Preemptive) ์ค์ผ์ค๋ง
> OS๊ฐ CPU ์ฌ์ฉ๊ถ์ ๊ฐ์  ํ์. ์ข๋ฃ๊น์ง ์คํ์ ๋ณด์ฅํ์ง ์๋๋ค. ์ฆ, ๋ ์ฒ๋ฆฌ์์๊ฐ ๋น ๋ฅธ ํ๋ก์ธ์ค๊ฐ ๋ค์ด์ค๊ฒ ๋๋ฉด ์คํ์ด ์ค๋จ๋๊ณ  CPU ์ ์ ๋ฅผ ๋นผ์๊ธด๋ค.
- #### โ๏ธ SRTF (Shortest Remaining Time First)
  - ์๋ก ๋์ฐฉํ ํ๋ก์ธ์ค๊ฐ ์์ ๋๋ง๋ค ๋ค์ Scheduling ํ๋ค.
  - ์ด๋ฏธ ์์ ์ค์ธ ํ๋ก์ธ์ค์ ๋จ์ BurstTime ๋ณด๋ค ๋ ์งง์ ์คํ ์๊ฐ์ ๊ฐ์ง ํ๋ก์ธ์ค๊ฐ ๋ค์ด์ค๊ฒ ๋๋ค๋ฉด. CPU์ ์ ๋ฅผ ๊ฐ์ ธ๊ฐ๊ฒ ๋๋ค.
  - ๐ **Starvation** ์ด ๋ฐ์ํ  ์ ์๋ค.
  
- #### โ๏ธ Priority Scheduling
  - ์ฐ์  ์์๋ฅผ ๋ถ์ฌํ์ฌ ์ฐ์ ์์๊ฐ ๋์ ์์๋๋ก ์ฒ๋ฆฌ. 
  - ์ฐ์  ์์๊ฐ ๋ฎ์ ํ๋ก์ธ์ค๊ฐ ๋ฌดํ์  ๊ธฐ๋ค๋ฆฌ๋ **Starvation** ์ด ๋ฐ์ํ  ์ ์๋ค.
  - **Aging** ์ ์ ์ฉํ์ฌ ํด๊ฒฐ ๊ฐ๋ฅ.(๋ค์ด์จ ์๊ฐ์ด ์ง๋ ์๋ก ์ฐ์ ์์๊ฐ ๋์์ง.)
  - ์ ์ ํ๊ณผ ๋น ์ ์ ํ์ด ์๋ค.
    > ์ ์ ํ์์๋ CPU ํ ๋น์ ๋นผ์๊ณ , ๋น ์ ์ ํ์์๋ Ready Queue์ ๋งจ ์์ ๋ฃ๋๋ค.

- #### โ๏ธ Round Robin
  - FCFS์ ๋์ ๊ฐ ํ๋ก์ธ์ค๋ ๋์ผํ TimeQuantum์ ํ ๋น ๋ฐ์ผ๋ฉฐ,
    ํด๋น ์๊ฐ๋งํผ ์ํํ ํ ๋ค๋ฅธ ํ๋ก์ธ์ค์๊ฒ CPU ์ ์ ๋ฅผ ๋๊ธฐ๊ณ  Queue์ ๋งจ ๋ค๋ก ์ด๋ํ๋ค.
  - Time Quantum ์ ๋๋ฌด ํฌ๊ฒ ์ฃผ๋ฉด FCFS๊ฐ ๋๊ณ , ๋๋ฌด ์ ๊ฒ ์ฃผ๋ฉด Context Switching ์ด ์์ฃผ ๋ฐ์ํ๋ฏ๋ก ์ค๋ฒํค๋ ์ฆ๊ฐ.
  
- #### โ๏ธ MultiLevel Queue(๋ค๋จ๊ณ ํ)
  - ์ฌ๋ฌ ๊ฐ์ ํ์ ์๋ก ๋ค๋ฅธ ์ค์ผ์ค๋ง ๊ธฐ๋ฒ์ ์ ์ฉ.
  - ์ฐ์ ์์๊ฐ ๋์ ์์(๋น ๋ฅธ ๋ฐ์ ์๋๋ฅผ ์๊ตฌํ๋ ์์)์๋ TimeQuantum์ ์๊ฒ.
  - ์ฐ์ ์์๊ฐ ๋ฎ์ ์์์๋ TimeQuantum์ ํฌ๊ฒ ํ ๋น.
  
- #### โ๏ธ MultiLevel Feedback Queue
  - ๋ค๋จ๊ณ ํ์ ์ ์ฌํ์ง๋ง Time Quantum์ ๋ชจ๋ ์๋ชจํ ํ๋ก์ธ์ค๊ฐ ๋ฐ์ ํ๋ก ์ด๋ํ๋ค.(์ฆ, ์ฐ์ ์์๊ฐ ํ๋จ๊ณ ๋ฎ์์ง.)
  - ๋๋ฌด ์ค๋ ๊ธฐ๋ค๋ฆฐ ์์ ๋ํ ์์ ํ๋ก ์ด๋ํ๋ค.
  - ์งง์ ์์ ์ฒ๋ฆฌ์ ์ ๋ฆฌ, Turn around Time ์ ์ค์ฌ์ค.(๋๊ธฐ + ์คํ์๊ฐ)
  
  
# ๐ DeadLock : ๊ต์ฐฉ์ํ
ํ๋ก์ธ์ค๊ฐ ์์์ ์ป์ง ๋ชปํด์ ๋ค์ ์ฒ๋ฆฌ๋ฅผ ํ์ง ๋ชปํ๋ ์ํ. ํ์ ์ ์ธ ์์์ ๊ณต์ ํ๊ธฐ ๋๋ฌธ์ ๋ฐ์ํ๋ค.

## ๐ง ๊ต์ฐฉ์ ๋ฐ์ ์กฐ๊ฑด
์ํธ ๋ฐฐ์ , ์ ์  ๋๊ธฐ, ๋น์ ์ข, ์ํ ๋๊ธฐ 4๊ฐ์ง๊ฐ ๋ชจ๋ ์ฑ๋ฆฝ๋  ๋ ๊ต์ฐฉ ์ํ๊ฐ ๋ฐ์ํ๋ค.

### โ๏ธ ์ํธ ๋ฐฐ์ (Mutual exclusion)
- ์์์ ํ ํ๋ก์ธ์ค์์๋ง ์ ๊ทผํ  ์ ์๋ค.(ํ๋ฒ์ ์ฌ๋ฌ ํ๋ก์ธ์ค์์ ํ ์์์ ์ฌ์ฉํ  ์ ์๋ค.)

### โ๏ธ ์ ์  ๋๊ธฐ (Hold and Wait)
- ์ต์ ํ๋์ ์์์ ์ ์ ํ๊ณ , ๋ค๋ฅธ ํ๋ก์ธ์ค์ ํ ๋น๋ ์์์ ๊ธฐ๋ค๋ฆฌ๋ ํ๋ก์ธ์ค๊ฐ ์กด์ฌ.

### โ๏ธ ๋น์ ์ (non-preemption)
- ๋ค๋ฅธ ํ๋ก์ธ์ค์ ํ ๋น๋ ์์์ ๊ฐ์ ๋ก ๋นผ์์ ์ ์๋ค.

### โ๏ธ ์ํ ๋๊ธฐ, ์ํ ๋๊ธฐ(Circular Wait)
- ํ๋ก์ธ์ค์ ์งํฉ์์ ์ํ ํํ๋ก ์์์ ๋๊ธฐ.
- 1๋ฒ ํ๋ก์ธ์ค๊ฐ ์์ A๋ฅผ ๊ฐ์ง๊ณ  ์์ B๋ฅผ ์์ฒญ, 2๋ฒ ํ๋ก์ธ์ค๊ฐ ์์ B๋ฅผ ๊ฐ์ง๊ณ  ์์ A๋ฅผ ์์ฒญ.

## ๐ง ๊ต์ฐฉ์ํ ํด๊ฒฐ

### โ๏ธ ์๋ฐฉ(prevention)
- ๊ต์ฐฉ์ํ ๋ฐ์ ์กฐ๊ฑด์ค ํ๋๋ฅผ ์ ๊ฑฐํ๋ค.(์์ ๋ญ๋น๊ฐ ์์)
  - ์ํธ ๋ฐฐ์  ๋ถ์ 
    > ๋ถ์ ํ  ์ ์๋ค.
  - ์ ์  ๋๊ธฐ ๋ถ์  
    > - ๋ฏธ๋ฆฌ ์ ์ ํ์ง ์๊ณ  ํ๋ก์ธ์ค ์คํ์  ๋ชจ๋  ์์์ ํ๋ฒ์ ํ ๋น.(์์ ๋ญ๋น)
    > - ์์์ ์ ์ ํ์ง ์๊ณ  ์์ ๋์๋ง ๋ค๋ฅธ ์์ ์์ฒญ ๊ฐ๋ฅ(๊ธฐ์ ์ํ ๋ฐ์ ๊ฐ๋ฅ)
  - ๋น์ ์  ๋ถ์ 
    > ๋ค๋ฅธ ํ๋ก์ธ์ค์์ ์์์ ์๊ตฌํ  ๋ ๊ฐ์ง ์์์ ๋ฐ๋ฉ.(์ด๋ฏธ ์คํ์ค์ด ์๊ธฐ ๋๋ฌธ์ ์์์ด ๋ญ๋น๋จ.)
  - ์ํ๋๊ธฐ ๋ถ์ 
    > ์์์ ๊ณ ์ ๋ฒํธ ํ ๋น ํ ์์๋๋ก ์์ ์๊ตฌ.
  
### โ๏ธ ํํผ(Avoidance)
- ๊ต์ฐฉ ์ํ ๋ฐ์์ ํผํ๋ค.
- #### โ๏ธ ์ํ์ ์๊ณ ๋ฆฌ์ฆ.
  - ๊ฐ์ 
    - ํ๋ก์ธ์ค ์๊ฐ ๊ณ ์ 
    - ์์์ ์ข๋ฅ์ ์๊ฐ ๊ณ ์ 
    - ํ๋ก์ธ์ค๊ฐ ์๊ตฌํ๋ ์์, ์ต๋ ์์์ ์๋ฅผ ์์์ผํ๋ค.
    - ํ๋ก์ธ์ค๋ ๋ฐ๋์ ์์์ ๋ฐ๋ฉ.

  - ํ๋ก์ธ์ค๊ฐ ์์์ ์๊ตฌํ  ๋, ์์คํ์ ์์์ ํ ๋น ํ ํ์๋ ์์  ์ํ์ธ์ง(๋ชจ๋  ํ๋ก์ธ์ค๊ฐ ์ ์์ ์ผ๋ก ์ข๋ฃ๊ฐ๋ฅํ ์ํ) ๊ฒ์ฌ ํ ํ ๋นํ๋ค.
  - ์์์ ํ ๋นํ์ ๋ ์์ ์ํ๊ฐ ์๋๋ผ๋ฉด ๋ค๋ฅธ ํ๋ก์ธ์ค์ ์์์ด ํด์ง๋ ๋๊น์ง ๋๊ธฐํ๋ค.
    > **์์  ์ํ?** ๋จ์ ์ฌ์  ์์์ผ๋ก ์ข๋ฃํ  ์ ์๋ ํ๋ก์ธ์ค ํ์ -> ํด๋น ํ๋ก์ธ์ค๋ฅผ ์ข๋ฃ์ํค๊ณ  ๋ฐํ๋ ์์์ผ๋ก ๋ค์ ํ์.. ์ ๋ฐ๋ณต์ ํตํด ๋ชจ๋  ํ๋ก์ธ์ค๋ฅผ ์ข๋ฃํ  ์ ์๋์ง.
  
- #### โ๏ธ์์ ํ ๋น ๊ทธ๋ํ ์๊ณ ๋ฆฌ์ฆ
  - ๊ทธ๋ํ๋ฅผ ๊ทธ๋ฆฌ๊ณ  ํฅํ ์์ฒญํ  ์ ์๋ ์์์ ์ ์ ์ ์์ฝ ๊ฐ์ ์ผ๋ก ํ์.
  - ์ฌ์ดํด์ด ๋ฐ์ํ๋ค๋ฉด ์์ ์์ฒญ์ ์น์ธํ์ง ์๋๋ค.

### โ๏ธ ๊ต์ฐฉ์ํ ํ์ฉ ํ ํ๋ณต

#### โ๏ธ ํ์ง(Detection)
์ผ์  ์ฃผ๊ธฐ๋ก ๊ต์ฐฉ ์ํ๊ฐ ๋ฐ์ํ๋์ง ํ์ง.
- ๋๊ธฐ ๊ทธ๋ํ
  - ์์ ํ ๋น ๊ทธ๋ํ์์ ์์์ ์ ๊ฑฐํ๊ณ  ๊ฐ์ ์ ๊ฒฐํฉํ ๊ทธ๋ํ.
  - ๋ค๋ฅธ ํ๋ก์ธ์ค์ ์์์ ๊ธฐ๋ค๋ฆฌ๋ ๊ฒ์ ํ์ดํ๋ก ํ์.
  - ์ฌ์ดํด์ด ์๊ธด๋ค๋ฉด ๊ต์ฐฉ์ํ๋ฅผ ์๋ฏธํ๋ค.
- ์ํ์ ์๊ณ ๋ฆฌ์ฆ : ๋ถ์์  ์ํ๋ผ๋ฉด ๊ต์ฐฉ์ํ๋ผ๊ณ  ํ๋จ.

#### โ๏ธ ํ๋ณต(Recovery)
๊ต์ฐฉ ์ํ๋ฅผ ์ผ์ผํจ ํ๋ก์ธ์ค๋ฅผ ์ข๋ฃํ๊ฑฐ๋, ํ ๋น๋ ์์์ ํด์ ํ์ฌ ๊ต์ฐฉ์ํ๋ฅผ ํ๋ณตํ๋ค.

#### 1. ํ๋ก์ธ์ค ์ข๋ฃ ๋ฐฉ๋ฒ
- ๊ต์ฐฉ ์ํ์ ํ๋ก์ธ์ค๋ฅผ ๋ชจ๋ ์ค์งํ๋ ๋ฐฉ๋ฒ. ๊ต์ฐฉ ์ํ๊ฐ ์ ๊ฑฐ๋  ๋ ๊น์ง ํ๋์ฉ ํ๋ก์ธ์ค๋ฅผ ์ค์ง์์ผ ๋๊ฐ.

#### 2. ์์ ์ ์ 
- ํฌ์์ ์ ํ : ์ต์์ ํผํด๋ฅผ ๊ฐ์ ธ์ค๋ ํ๋ก์ธ์ค ์ ํํ ์์์ ํด์ , ๋ค๋ฅธ ํ๋ก์ธ์ค์๊ฒ ํ ๋น.
  > - ์ฐ์ ์์๊ฐ ๋ฎ์ ํ๋ก์ธ์ค, ์คํ ๋น๋๊ฐ ๋ฎ์ ํ๋ก์ธ์ค๋ฅผ ์ฃผ๋ก ์ ํํ๋ค.
  > - ๊ธฐ์ ์ํ๊ฐ ๋ฐ์ํ  ์ ์์ผ๋ฏ๋ก ์ด๋ฅผ ํด๊ฒฐํ๋ ๋ฐฉ๋ฒ์ด ํ์.(์์์ ์ ์  ๋นํ ๋ ๋ง๋ค ์ฐ์ ์์ ์ฆ๊ฐ ๋ฑ.)
- ๋กค๋ฐฑ : ์ ์ ๋ ํ๋ก์ธ์ค๋ฅผ ์ด์  ์ํ๋ก ๋กค๋ฐฑ(์ค๋จ ํ ์ฌ์์)
  
#### 3.๏ธ ์ฌ์ฉ์๊ฐ ์ง์  ํด๊ฒฐ


# ๐ ๋๊ธฐํ, ๊ฒฝ์ ์ํ(Race Condition)
๋์ ์ ๊ทผ์ผ๋ก ์ํ๋ ๊ฒฐ๊ณผ๊ฐ ๋ฐ์ํ์ง ๋ชปํ๋ค. ๋น ์ผ๊ด์ฑ.
## ๐ง ๋ฐ์
### โ๏ธ ์ปค๋ ์์์ ์ํํ๋ ์ค์ ์ธํฐ๋ฝํธ ๋ฐ์
- ์ปค๋๋ชจ๋์์ ์์์ ์ํํ๋ค๊ฐ ์ธํฐ๋ฝํธ๊ฐ ๋ฐ์, ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ ์กฐ์ํ๋ ๊ฒฝ์ฐ.
- ์ปค๋ ๋ชจ๋์์ ์์์ ์ํํ๋ ๋์ ์ธํฐ๋ฝํธ๋ฅผ Disable ์์ผ ๋ฐ์์ ๋ง๋๋ค.

### โ๏ธ System Call
- ํ๋ก์ธ์ค 1์์ ๋ฐ์ดํฐ๋ฅผ ์กฐ์ํ๋ ๋์ค ์๊ฐ์ ๋ชจ๋ ์๋ชจํ์ฌ CPU ์ ์ด๊ถ์ด ํ๋ก์ธ์ค 2๋ก ๋์ด๊ฐ๊ฒ ๋๊ณ , ๊ฐ์ ๋ฐ์ดํฐ๋ฅผ ์กฐ์
- ์๊ฐ์ ๋ชจ๋ ์๋ชจํ์ฌ๋ CPU ์ ์ด๊ถ์ ๋๊ธฐ์ง ์๋๋ก ํ๋ค.

### โ๏ธ ๋ฉํฐ ํ๊ฒฝ์์ ๊ณต์  ๋ฉ๋ชจ๋ฆฌ์ ์ ๊ทผ
- 2๊ฐ์ ํ๋ก์ธ์ค ๋๋ ์ค๋ ๋๊ฐ ๊ณต์  ๋ฐ์ดํฐ์ ์ ๊ทผํ์ฌ ๋ฐ์ดํฐ๋ฅผ ์กฐ์.
- ๋ฐ์ดํฐ๋ฅผ ์ฌ์ฉํ  ๋ lock์ ๊ฑธ์ด๋๋ค.

## ๐ง ํด๊ฒฐ
#### ๐ ์๊ณ ๊ตฌ์ญ(Critical Section) : ๊ณต์  ๋ฐ์ดํฐ์ ์ ๊ทผํ๋ ๋ถ๋ถ.
๊ณต์  ๋ฐ์ดํฐ์๋ ํ๋์ ํ๋ก์ธ์ค๋ง ์ ๊ทผํด์ผ ํ๋ค.
### โ๏ธ ํด๊ฒฐ์ ์ํ ๊ธฐ๋ณธ ์กฐ๊ฑด
- ์ํธ ๋ฐฐ์ (Mutual Exclusion): ๋จ ํ๋์ ํ๋ก์ธ์ค๋ง ์๊ณ ๊ตฌ์ญ์ ์ ๊ทผ.
- ์งํ (Process): ์๊ณ ๊ตฌ์ญ์ ๋ค์ด๊ฐ๊ณ ์ ํ๋ ํ๋ก์ธ์ค๋ ๋ฌดํ์  ๋๊ธฐํ  ์ ์๋ค.(์๊ณ ๊ตฌ์ญ์ด ๋น์ด์๋ค๋ฉด ๋ฐ๋์ ๋ค์ด๊ฐ์ผ ํ๋ค.)
- ํ๊ณ ๋๊ธฐ(Bounded Waiting): ์ธ์  ๊ฐ๋ ์์ ์ ์ฐจ๋ก๊ฐ ๋์์ ์๊ฒ๊ตฌ์ญ์ ๋ค์ด๊ฐ์ผ ํ๋ค.

### โ๏ธ ์ธ๋งํฌ์ด(Semaphore)
๋ฉํฐ ํ๊ฒฝ์์ ๊ณต์ ์์์ ๋ํ ์ ๊ทผ ์ ํ.
```java
wait(s) {
    // ์๊ณ ๊ตฌ์ญ์ด ๋ชจ๋ ์ฐจ์์ผ๋ฉด ๋๊ธฐ.
	while(s <= 0);
	s--;
}
// ์๊ณ ๊ตฌ์ญ์์ ๋์์ ๋ ์คํ. 
signal(s) {
	s++:
}
```
- ํ๋๊ทธ๋ฅผ ๋๊ณ  ๋ฐ์ดํฐ์ ์ ๊ทผํ ๋ ์ ๊ทผํจ์ ํ์ํ๋ค.
- Wait, Signal ๋ฉ์๋ ์ฌ์ฉํ๋ค.
- ์ฌ์ฉํ๊ณ  ๋์จ ๋ค์๋ ๋ค์ ํ๋๊ทธ๋ฅผ ๋ณ๊ฒฝํด๋๋ฉด ๋ค๋ฅธ ํ๋ก์ธ์ค๊ฐ ์ด๋ฅผ ํ์ธํ๊ณ  ์ ๊ทผํ๋ค.
- while ๋ฌธ์ ์ฌ์ฉํ์ฌ flag๋ฅผ ํ์ธํ๋ฉฐ ๋๊ธฐํ๋ค.
- int ํ ๋ณ์๋ฅผ ์ฌ์ฉํ๋ฉด counting semaphore, binary๋ฅผ ์ฌ์ฉํ๋ฉด binary semaphore ๋ผ๊ณ  ํ๋ค.
- binary semaphore๋ mutex Lock๊ณผ ๋์ผํ๋ค.

#### ๐ while๋ฌธ์ ์ด์ฉํ wait๋ busy waiting์ด๊ธฐ ๋๋ฌธ์ ์ฑ๋ฅ์ ์ข์ง ์๋ค.
> ๋๋ฌธ์ ๋๊ธฐ์ด(Queue)๋ฅผ ์ด์ฉํ์ฌ ์๊ณ๊ตฌ์ญ์ด ๊ฐ๋ ์ฐผ๋ค๋ฉด Queue์ ํด๋น ํ๋ก์ธ์ค๋ฅผ ์ถ๊ฐํ์ฌ ๋๊ธฐํ๋๋ก ๊ตฌํํ  ์ ์๋ค.


### โ๏ธ ๋ฎคํ์ค ์๊ณ ๋ฆฌ์ฆ
- Lock์ ์ต๋๊ณผ ํด์  ๋๊ฐ์ง์ ์ฐ์ฐ์ ์ ๊ณตํ๋ค. Lock์ ๊ฐ์ง ํ๋ก์ธ์ค๋ง์ด ์๊ณ๊ตฌ์ญ์ ์ ๊ทผํ  ์ ์๋ค. 
- ๋๊ตฐ๊ฐ Lock์ ๊ฐ์ง๊ณ  ์๋ค๋ฉด ๊ธฐ๋ค๋ฆฌ๊ณ , Lock์ด ํด์ ๋๋ฉด ์์ ์ด Lock์ ๊ฐ์ง๊ณ  ๋ค์ด๊ฐ ์์์ ์ํํ ํ Lock์ ํด์ ํ๋ค.
- ์ํํธ์จ์ด์  ํด๊ฒฐ์ ์ฑ๋ฅ์ด ์ข์ง ์์ ํ์ฌ๋ ์ ์ฌ์ฉํ์ง ์๋๋ค.
  ```java
  acquire() {
      // ๋ค๋ฅธ ํ๋ก์ธ์ค๊ฐ ๋ค์ด๊ฐ์์ผ๋ฉด ๋๊ธฐ
      while (!available);
      abilable = false;
  }
  // ์์ ํด๋ฐฉ unlock
  release() {
      abilable = true;    
  }
   
  do {
      acquire(); //lock
      //{critical section}
      release() // unlock
      // {remainder section}
  } while(true);
  ```

- #### โ๏ธ๏ธ๏ธ ๋ฐ์ปค(Dekker) ์๊ณ ๋ฆฌ์ฆ
  ```java
  flag[i] = true; 
  while(flag[j]) { 
      if(turn == j) { 
          flag[i] = false; 
          while(turn == j); 
          flag[i] = true; 
      }
  }
  //{Critical Section}
  turn = j; 
  flag[i] = false; 
  ```
  - flag์ turn์ ์ด์ฉํ์ฌ ์๊ณ ๊ตฌ์ญ์ ๋ค์ด๊ฐ ํ๋ก์ธ์ค๋ฅผ ๊ฒฐ์ .
  - flag : ๋๊ฐ ์๊ณ ๊ตฌ์ญ์ ์ง์ ํ  ๊ฒ์ธ์ง.
  - turn : ๋๊ฐ ์๊ณ ๊ตฌ์ญ์ ์ง์ํ  ์ฐจ๋ก์ธ์ง.
  - j์ turn ์ด๋ผ๋ฉด ๊ธฐ๋ค๋ฆฌ๋ค๊ฐ j๊ฐ ๋๋๊ณ  turn์ i๋ก ์ค์ ํ๋ฉด ์ง์.
  - ์ฌ์ฉํ๊ณ  ๋๋ฉด ์์ ์ ํ๋๊ทธ๋ฅผ false๋ก ํ๊ณ , turn ์ ๋๊ฒจ์ค๋ค.
  
- #### โ๏ธ๏ธ ํผํฐ์จ(Peterson) ์๊ณ ๋ฆฌ์ฆ
  - ๋ค๋ฅธ ํ๋ก์ธ์ค์๊ฒ ์ง์ ๊ธฐํ๋ฅผ ๋จผ์  ์๋ณด.
  - ๋ค๋ฅธ ํ๋ก์ธ์ค๊ฐ ์ง์ ์๋์ค์ด๋ผ๋ฉด ๊ธฐ๋ค๋ฆผ.

  ```java
  flag[i] = true;
  turn = j;
  while(flag[j] && turn == j){}
  // { Critical Section}
  flag[i] = false;
  ```

- #### โ๏ธ Bakery ์๊ณ ๋ฆฌ์ฆ
  - ๋ฒํธํ๋ฅผ ๋ฐ๊ธ๋ฐ๊ณ  ๊ฐ์ฅ ์ ์ ์์ ๋ฒํธํ๋ฅผ ๊ฐ์ง ํ๋ก์ธ์ค๊ฐ ๋จผ์  ์ ๊ทผ.

### โ๏ธ Monitor
- ํ๋์ ํ๋ก์ธ์ค ๋ด์ ๋ค๋ฅธ ์ฐ๋ ๋๊ฐ์ ๋๊ธฐํ์ ์ฌ์ฉ๋๋ค.
- ๋ชจ๋ํฐ๋ ์ฃผ๋ก ํ๋ ์์ํฌ๋ ๋ผ์ด๋ธ๋ฌ๋ฆฌ์์ ์ ๊ณต๋๋ค.
- java์์๋ synchronized, wait(), notify()๋ฅผ ์ฌ์ฉ.
- Mutual Exclusion Queue ์ Conditional Synchronized Queue ๊ฐ ์กด์ฌํ๋ค.
- **Mutual Exclusion Queue** ๋ ์๊ณ๊ตฌ์ญ์ด ์ฌ์ฉ์ค์ผ ๋ ๋ค๋ฅธ ์ฐ๋ ๋๊ฐ ๋๊ธฐํ๋ ์ฅ์์ด๊ณ ,     
  **Conditional Queue**๋ ์๊ณ๊ตฌ์ญ์์ ์ฐ๋ ๋๊ฐ wait()๋ฅผ ํธ์ถ ํ์ ๋ ํด๋น ์ฐ๋ ๋๊ฐ ๋๊ธฐํ๋ ์ฅ์์ด๋ค. ํ์ notify()๋ก ๊นจ์ด๋ ๋ค์ ํ๋ ์์์ ๊ณ์ํ๋ค.

# ๐ ๋ฉ๋ชจ๋ฆฌ
### ๐ค๏ธ ๋ฉ๋ชจ๋ฆฌ์ ๊ณ์ธต ๊ตฌ์กฐ
![img_6.png](img/img_6.png)
- ์ ๊ทผ์๋์ ์ฉ๋.
  - ๋ฉ๋ชจ๋ฆฌ์ ๊ณ์ธต์์ ์๋๋ก ๊ฐ์๋ก ์ฉ๋์ ์ปค์ง์ง๋ง ๋ฐ์ดํฐ์ ์ ๊ทผ ์๋๋ ๋๋ ค์ง๋ค.
- ํ๋ฐ์ฑ
  - register ๋ถํฐ RAM๊น์ง๋ ํ๋ฐ์ฑ, ๊ทธ ์ดํ๋ ๋น ํ๋ฐ์ฑ์ ๊ฐ์ง๋ค.

### ๐ค I/O์์์ ๋ฉ๋ชจ๋ฆฌ ์ง์  ์ ๊ทผ(Direct Memory Access)
- I/O ์์ถ๋ ฅ์ ๋น๋ฒํ๊ฒ ๋ํ๋๊ณ , CPU๊ฐ ์ด๋ฅผ ๊ธฐ๋ค๋ฆฌ๊ธฐ์๋ ์๋๊ฐ ๋๋ฌด ๋๋ฆฌ๋ค.
  ๋๋ฌธ์ DMA(Direct Memory Access) ์ปจํธ๋กค๋ฌ๊ฐ ์ด๋ฅผ ์ค์ฌํ๋ฉฐ I/O ์๋ ฅ์ ๋ํด ๋ฐ๋ก RAM์ ์ ๊ทผํ  ์ ์๋๋ก ํด์ค๋ค.
- CPU๊ฐ ์ฒ๋ฆฌํ  ์ผ์ ๋์  ํด์ฃผ๊ธฐ ๋๋ฌธ์ CPU์ ํจ์จ์ด ์ฌ๋ผ๊ฐ๋ค. DMA ๋ ๋ฐ์ดํฐ์ ์ ์ก์ด ์๋ฃ๋๋ฉด ์ผ์  ๋ฐ์ดํฐ(Block) ๋ง๋ค Interrupt๋ฅผ ๋ฐ์์ํจ๋ค.


- **๋ฉ์ธ ๋ฉ๋ชจ๋ฆฌ**๋ CPU๊ฐ ์ง์  ์ ๊ทผํ  ์ ์๋ ๊ธฐ์ต ์ฅ์น.
  > CPU๋ Disk์ ์ ๊ทผํ  ์ ์๊ธฐ ๋๋ฌธ์ ํ๋ก๊ทธ๋จ์ ์คํํ๊ธฐ ์ํด์๋ ๋ฉ๋ชจ๋ฆฌ์ ํ๋ก๊ทธ๋จ์ ์ฌ๋ ค์ผ ํ๋ค.
- ์ฃผ์๊ฐ ํ ๋น๋ ์ผ๋ จ์ ๋ฐ์ดํธ.
- ๋ช๋ น์ด, ๋ช๋ น์ด ์ํ์ ๋ฉ๋ชจ๋ฆฌ์ ํ์ํ ๋ฐ์ดํฐ๋ฅผ ๊ฐ์ ธ์ด.

### ๐ค๏ธ Binding Time
- loading Time: ์ปดํ์ผ ์์ ์ ์๋ ์ฃผ์๋ฅผ ์ด์ฉํ๊ณ , ๋ก๋ฉ ์์ ์ Loader๊ฐ ์๋ ์ฃผ์๋ฅผ ์ ๋ ์ฃผ์๋ก ๋ณํ.
  ํ๋ก๊ทธ๋จ ๊ตฌ๋ ์๊ฐ์ด ์ค๋๊ฑธ๋ ค ์ ์ฌ์ฉํ์ง ์๋๋ค.
  
- Execution Time: ๋ฐ์ธ๋ฉ์ด ๊ฐ์ฅ ๋ฆ๊ฒ ์ผ์ด๋๊ธฐ ๋๋ฌธ์ ์ ์ฐ์ฑ์ด ๋๊ณ , MMU๊ฐ ๋ผ๋ฆฌ์ฃผ์๋ฅผ ์ค์  ์ฃผ์๋ก ๋ณํํด์ค๋ค.
MMU์ Base Register๊ฐ์ CPU๊ฐ ๋๊ธด ๋ผ๋ฆฌ์ฃผ์ ๊ฐ์ ๋ํด ๋ฌผ๋ฆฌ ๋ฉ๋ชจ๋ฆฌ ์ฃผ์๋ฅผ ๊ตฌํ๋ค.

### ๐ค ๋ฉ๋ชจ๋ฆฌ ๊ด๋ฆฌ์ฅ์น MMU(Memory Management Unit)
- ๋ฉ๋ชจ๋ฆฌ ๋ณดํธ, ์บ์ ๊ด๋ฆฌ ๋ฑ CPU๊ฐ ๋ฉ๋ชจ๋ฆฌ์ ์ ๊ทผ ํ๋๊ฒ์ ๊ด๋ฆฌํ๋ค.
- ๋ผ๋ฆฌ ์ฃผ์๋ฅผ ๋ฌผ๋ฆฌ์ฃผ์๋ก ๋ณํํ๋ค.

- ํ๋ก์ธ์ค์๊ฒ ํฉ๋ฒ์ ์ธ ์ฃผ์ ์์ญ์ ์ค์ ํ๋ค. ์๋ชป๋ ์ ๊ทผ์๋ํด Trap์ ๋ฐ์์์ผ ๋ณดํธ.
  - base : ๋ฉ๋ชจ๋ฆฌ์์ ํ๋ก์ธ์ค ์์ ์ฃผ์.
  - limit : ํ๋ก์ธ์ค์ ์ฌ์ด์ฆ.
  - ์ฆ ์ ๊ทผ ๋ฒ์๋ base ์ด์ base + limit ๋ฏธ๋ง.
  
## ๐ง Fragment
![img_1.png](img/img_8.png)    
ํ๋ก์ธ์ค๋ค์ด ์ฐ์๋ ๊ณต๊ฐ์ ํ ๋น ๋์๋ค๊ฐ. ์ผ๋ถ๊ฐ ์ข๋ฃ๋๋ค๋ฉด ๋ฉ๋ชจ๋ฆฌ ๊ณต๊ฐ์ ์ฐ์๋ ๊ณต๊ฐ์์ ์ค๊ฐ์ค๊ฐ์ด ๋น์ด์๋ ๋ชจ์ต์ด ๋  ๊ฒ์ด๋ค.
์ด๋ฅผ **์ธ๋ถ ๋จํธ(External Fragment)** ์ด๋ผ๊ณ  ํ๋ค.

์ธ๋ถ ๋จํธํ๊ฐ ๋ฐ์ํ๋ฉด ์ค์ ๋ก๋ ๋จ์ ์๋ ๊ณต๊ฐ์ด ์์ง๋ง ํ๋ก์ธ์ค๋ฅผ ์คํ์ํค์ง ๋ชปํ๊ฒ ๋๋ค.

#### โ๏ธ Compaction
> ์ธ๋ถ ๋จํธ์ ์ ๊ฑฐํ๊ธฐ ์ํด ๋ชจ๋  ํ๋ก์ธ์ค๋ฅผ ํ์ชฝ์ผ๋ก ๋ฏธ๋๊ฒ, ๋น์ฉ์ด ๋งค์ฐ ํฌ๊ณ  ์ํํ๋ค.

## ๐ง Paging
![img_2.png](img/img_9.png)

- ํ์ด์ง์ ์ด์ฉํ๋ค๋ฉด ํ๋์ ํ๋ก์ธ์ค๋ฅผ ์ฐ์๋ ๊ณต๊ฐ์ ํ ๋นํ  ํ์๊ฐ ์๊ธฐ ๋๋ฌธ์ ์ธ๋ถ ๋จํธํ ๋ฌธ์ ๊ฐ ํด๊ฒฐ๋๋ค.
- ๋ฉ๋ชจ๋ฆฌ๋ฅผ ์ผ์ ํ ํฌ๊ธฐ๋ก ๋๋ ๊ฒ์ **Frame**, ํ๋ก์ธ์ค๋ฅผ ์ผ์ ํ ํฌ๊ธฐ๋ก ๋๋ ๊ฒ์ **Page** ๋ผ๊ณ  ํ๋ค.
- **Internal Fragmentation(๋ด๋ถ ๋จํธํ)** ์ด ๋ฐ์ํ์ง๋ง ์ธ๋ถ ๋จํธ์ ๋นํ๋ฉด ํจ์ฌ ์๋ค.
  > ๐ก Internal Fragment: ํ๋ก์ธ์ค๊ฐ ์ค์ ๋ก ํ ๋น ๋ฐ์์ง๋ง ์ฌ์ฉํ์ง ์๋ ๊ณต๊ฐ.
- ํ์ด์ง์ ์ด์ฉํ๊ฒ ๋๋ฉด Page Table ์ด MMU์ ์ญํ ์ ํ๊ฒ ๋ค๋ค. Page Table์์ Page Number๋ฅผ ๊ฐ์ง๊ณ  ๋ผ๋ฆฌ ์ฃผ์๋ฅผ ๋ฌผ๋ฆฌ ์ฃผ์๋ก ๋ณํํด์ค๋ค.(frame์ ์ฐพ์์ค๋ค.)

### โ๏ธ TLB(Translation Lookaside Buffer)
- ํ์ด์ง ํ์ด๋ธ์ ํ๋ฒ, ์ค์  ์ฃผ์๋ก ์ ๊ทผ ํ๋ฒ , ๋๋ฒ์ ๋์คํฌ ์ ๊ทผ์ด ํ์ํ๊ธฐ ๋๋ฌธ์ ๋๋ฐฐ์ ์๊ฐ์ด ๊ฑธ๋ฆฐ๋ค.    
  ์ด๋ฅผ ํด๊ฒฐํ๊ธฐ ์ํด **ํ์ด์ง ํ์ด๋ธ์ ์บ์ฑ**ํ๋ค.(TLB)
- ํ์ด์ง ํ์ด๋ธ์ ํ์ํ ๋ TLB์์ ์ฐ์ ์ ์ผ๋ก ํ์ํ๋ค. Cache Hit๊ฐ ๋งค์ฐ ๋๋ค.

## ๐ง ๊ฐ์ ๋ฉ๋ชจ๋ฆฌ
๋ค์ค ํ๋ก๊ทธ๋๋ฐ์ ์คํํ๊ธฐ ์ํด์๋ ๋ง์ ํ๋ก์ธ์ค๋ฅผ ๋์์ ๋ฉ๋ชจ๋ฆฌ์ ์ฌ๋ ค์ผ ํ๋ค.
๊ฐ์ ๋ฉ๋ชจ๋ฆฌ๋ ํ๋ก์ธ์ค ์ ์ฒด๊ฐ ์ฌ๋ผ์ค์ง ์๋๋ผ๋ ์คํ์ด ๊ฐ๋ฅํ๋๋ก ํ๋ ๊ธฐ๋ฒ์ด๋ค.

์ ์ฒด ํ๋ก์ธ์ค์ ๋ฉ๋ชจ๋ฆฌ๊ฐ ํ๋ฒ์ ์ฌ์ฉ๋๋ ๊ฒฝ์ฐ๊ฐ ๊ฑฐ์ ์๊ธฐ ๋๋ฌธ์, ์ฌ์ฉํ์ง ์๋ ๋ถ๋ถ์ ๋์คํฌ์ ์ฎ๊ฒจ ๋๋๋ค.(swapping)
์ด๋ก์ ๋ฉ๋ชจ๋ฆฌ๋ณด๋ค ๋ ํฐ ํ๋ก์ธ์ค๋ฅผ ์คํํ  ์ ์๊ฒ ๋๋ค (RAM + Disk).


  
### โ๏ธ ์๊ตฌ ํ์ด์ง(Demand Paging)
ํ๋ก์ธ์ค์์ ํ์ด์ง์ ์ ๊ทผํ๋ ค ํ๋๋ฐ ํด๋น ๋ถ๋ถ์ด ๋ฉ๋ชจ๋ฆฌ์ ์ฌ๋ผ์์์ง ์์ ๋์คํฌ์์ ์ฐพ๋ ๊ฒ.(์ด๊ธฐ์๋ ํ์ํ ๊ฒ๋ค๋ง ๋ฉ๋ชจ๋ฆฌ์ ์ฌ๋ฆผ.)
- ํ์ด์ง ๋ฒํธ๊ฐ ์ ํจํ์ง ๊ฒ์ฌ -> ์ ํจํ์ง ์์ผ๋ฉด page fault ๋ฐ์.
  - Invalid reference: ์ ํจํ์ง ์์, Abort ๋ฐ์.
  - Just Not In Memory: ๋ฉ๋ชจ๋ฆฌ์ ์์ -> ๋์คํฌ์์ ํ์.


### โ๏ธ ํ์ด์ง ๊ต์ฒด
๊ฐ์ ๋ฉ๋ชจ๋ฆฌ๋ฅผ ์ด์ฉํ์ฌ ๋ฉ๋ชจ๋ฆฌ๋ฅผ ํ ๋น ํ์ ๋ ๊ณผํ ๋น์ด ๋ฐ์ํ  ์ ์๋ค.
์ด๋ฅผ ํด๊ฒฐํ๊ธฐ ์ํด ํ๋ก์ธ์ค๋ฅผ Swap outํ๊ณ , ์ด ๊ณต๊ฐ์ ๋น ํ๋ ์์ผ๋ก ํ์ฉํ์ฌ ํด๋น ๋น ํ๋ ์์ ํ์ด์ง๋ฅผ ์ฌ๋ฆฐ๋ค.

1. ํ์ด์ง ํดํธ ๋ฐ์   
2. ํ์ด์ง ํดํธ๊ฐ ๋ฐ์ํ ํ์ด์ง๋ฅผ ๋์คํฌ์์ ํ์.
3. ๋ฉ๋ชจ๋ฆฌ์ ๋น ํ๋ ์ ํ์.    
> - ์์ผ๋ฉด ๋น ํ๋ ์ ์ฌ์ฉ, ์๋ค๋ฉด ํ์ด์ง ๊ต์ฒด ์๊ณ ๋ฆฌ์ฆ์ ์คํํ์ฌ ํฌ์ ํ๋ ์์ ์ ์ .
> - ๊ต์ฒด๋  ํ์ด์ง๋ฅผ ๋์คํฌ์ ๊ธฐ๋กํ์ฌ ์ ์ฅ.
4. ๋น ํ๋ ์์ ํดํธ๊ฐ๊ฐ ๋ฐ์ํ ํ์ด์ง๋ฅผ ์ฌ๋ฆฌ๊ณ  ํ์ด์ง ํ์ด๋ธ ์๋ฐ์ดํธ.

#### ๐ ์ค๋ฒํค๋ ๋ฐ์
ํฌ์ ํ๋ ์์ ๋น์ธ ๋, ์ํ๋ ํ์ด์ง๋ฅผ ํ๋ ์์ ์ฌ๋ฆด ๋, ๋๋ฒ์ ๋์คํฌ ์ ๊ทผ์ด ์ด๋ฃจ์ด์ง๋ค.
์ ์ ํ ๊ต์ฒด๋ก ์ค๋ฒํค๋๋ฅผ ์ค์ฌ์ผ ํจ.

### โ๏ธ ํ์ด์ง ๊ต์ฒด ์๊ณ ๋ฆฌ์ฆ
์์ฐ๋ ํ์ด์ง๋ฅผ ๋ด๋ฆฌ๊ณ , ํ์ฌ ํ์ํ ํ์ด์ง๋ฅผ ์ถ๊ฐํ  ๋ ์ด๋ค ํ์ด์ง๋ฅผ ๋ด๋ ค์ผํ ์ง ๊ฒฐ์ ํ๋ ์๊ณ ๋ฆฌ์ฆ.

#### โ๏ธ FIFO
- ๊ฐ์ฅ ๋จผ์  ์ฌ๋ผ์จ(์ฌ๋ผ์จ์ง ์ค๋๋) ํ์ด์ง๋ฅผ out ์ํจ๋ค.
- ์ด๊ธฐํ ์ฝ๋๋ฅผ out ์ํฌ๋ ์ ์ .
- ํ๋ฐํ ์ฌ์ฉ๋๋ ํ์ด์ง๊ฐ out๋  ์ ์์ด ๋ง์ page fault๋ฅผ ๋ฐ์์ํฌ ์ ์๋ค.

#### โ๏ธ Optimal ์๊ณ ๋ฆฌ์ฆ
- ์์ผ๋ก ๊ฐ์ฅ ์ฌ์ฉํ์ง ์์ ํ์ด์ง๋ฅผ ์ฐ์ ์ ์ผ๋ก ๋ด๋ณด๋ธ๋ค.
  - ํ์ง๋ง ์ค์ง์ ์ผ๋ก ์ฌ์ฉํ์ง ์์์ ๋ณด์ฅํ  ์ ์๊ธฐ ๋๋ฌธ์ ์ํํ๊ธฐ ์ด๋ ค์.
- FIFO์  ๋นํด ํ์ด์ง ๊ฒฐํจ์ ํ์๊ฐ ๋ง์ด ๊ฐ์๋๋ค. 

#### โ๏ธ LRU(Least-Recently-Used)
- ์ต๊ทผ์ ์ฌ์ฉํ์ง ์์๋ค๋ฉด ๋ค์ ์ฌ์ฉ๋  ํ๋ฅ ์ด ๋ฎ๋ค๋ ์์ด๋์ด.
- ์ฌ์ฉํ์ง ๊ฐ์ฅ ์ค๋๋ ํ์ด์ง๋ฅผ ๊ต์ฒดํ๋ค.
- ๊ณผ๊ฑฐ๋ฅผ ๋ณด๊ธฐ ๋๋ฌธ์ ์ค์ ๋ก ์ฌ์ฉํ  ์ ์๋ค. ํ์ง๋ง ๋ฉ๋ชจ๋ฆฌ์ 2๋ฒ ์ ๊ทผํด์ผ ํ๊ธฐ ๋๋ฌธ์ ์ค์ ๋ก ์ฌ์ฉํ๊ธฐ์๋ ์ ํ์ด ๋ฐ๋ฅธ๋ค.

#### โ๏ธ LRU ๊ทผ์ฌ ์๊ณ ๋ฆฌ์ฆ
- ํ์ด์ง์ ์ฐธ์กฐ ๋นํธ๋ฅผ ๋๊ณ  ์ฐธ์กฐ ๋์์ผ๋ฉด 1, ์ฐธ์กฐ ๋์ง ์์์ผ๋ฉด 0์ผ๋ก ๋๋ค. ์ฃผ๊ธฐ์ ์ผ๋ก bit๋ฅผ 0์ผ๋ก ์ด๊ธฐํ ํ๋ค.
- Second Chance Algorithm: ๊ต์ฒดํ  ํ์ด์ง๋ฅผ ํ์ํ๋ฉฐ bit๊ฐ 0์ด๋ผ๋ฉด ์ ํ, 1์ด๋ผ๋ฉด 0์ผ๋ก ๋ฐ๊พธ๊ณ  ๋์ด๊ฐ๋ค.
  > Modified bit๋ฅผ ์ถ๊ฐํ์ฌ ๋ณ๊ฒฝ๋์ง๋, ์ฐธ์กฐ๋์ง๋ ์์ ํ์ด์ง๋ฅผ ์ ํํ  ์๋ ์๋ค.
  
#### โ๏ธ ์นด์ดํ ์๊ณ ๋ฆฌ์ฆ
- LFU(Least Frequently Used): ์ฐธ์กฐ๋  ๋๋ง๋ค ์นด์ดํธํด ์ฐธ์กฐ ํ์๊ฐ ์ ์ ๊ฒ์ ์ ํ.
- MFU(Most Frequently Used): ์ฐธ์กฐ ํ์๊ฐ ๋ง์ ๊ฒ์ ์ ํ.

### ๐ค ์บ์
์ฌ์ ๊ทผ ํ ๋ ๋ฉ๋ชจ๋ฆฌ์ ์ฐธ์กฐ ๋น์ฉ์ ์ค์ด๊ธฐ ์ํด ์ฌ์ฉ๋๋ค. ์์ฃผ ์ฐธ์กฐ๋๋ ๊ฒ์ ๊ณ ์์ผ๋ก ์ ๊ทผ ๊ฐ๋ฅํ ์์น์ ๋๊ณ  ์ฌ์ฉํ๋ค.
- CPU์์ ์ฃผ์๋ฅผ ์ ๋ฌ -> ์บ์์์ ๋จผ์  ํ์ธ -> Hit์ด๋ฉด ๋ช๋ น์ด๋ฅผ ๊ฐ์ ธ์ด, Miss๋ฉด ์ฃผ๊ธฐ์ต ์ฅ์น์ ์ ๊ทผ.
- ์บ์ฑ ๋ผ์ธ : ์บ์ ๋ฐ์ดํฐ๋ฅผ ์ ์ฅํ  ๋ ์๋ฃ๊ตฌ์กฐ๋ฅผ ํ์ฉ(map,set ๋ฑ)

- #### ์ง์ญ์ฑ
  - ์๊ฐ ์ง์ญ์ฑ : ์ต๊ทผ์ ์ฐธ์กฐ๋ ์ฃผ์๊ฐ ๊ณง ๋ค์ ์ฐธ์กฐ๋  ๊ฒ์ด๋ค.
  - ๊ณต๊ฐ ์ง์ญ์ฑ : ํ๋ก๊ทธ๋จ์ด ์ฐธ์กฐ๋ ์ฃผ์์ ์ธ์  ์ฃผ์์ ๋ด์ฉ์ด ๋ค์ ์ฐธ์กฐ๋  ๊ฒ์ด๋ค.


# ๐ ํ์ผ ์์คํ
## ๐ง ์ ๊ทผ
### โ๏ธ ์์ฐจ ์ ๊ทผ
- ํ์ฌ ์์น ํฌ์ธํฐ์์ ํฌ์ธํฐ๋ฅผ ์ด๋ํ๋ฉฐ ์์ฐจ์ ์ผ๋ก ์ ๊ทผ.(read, write)

### โ๏ธ ์ง์  ์ ๊ทผ
- ๋ฌด์์ ํ์ผ ๋ธ๋ก์ ๋ํ ์์ ์ ๊ทผ ๊ฐ๋ฅ.
- reset : cp = 0;
- read_next : read cp; cp = cp +1;
- write_next : write cp; cp = cp + 1;

## ๐ง ๋๋ ํ ๋ฆฌ ๊ตฌ์กฐ
### โ๏ธ 1๋จ๊ณ ๋๋ ํ ๋ฆฌ
![img_2.png](img/img_2.png)
- ํ์ผ, ๋๋ ํ ๋ฆฌ ๋ค์ ์๋ก ์ ์ผํ ์ด๋ฆ์ ๊ฐ์ง.

### โ๏ธ 2๋จ๊ณ ๋๋ ํ ๋ฆฌ
![img_3.png](img/img_3.png)
- ์ฌ์ฉ์ ๋ณ๋ก ๊ฐ๋ณ์ ์ธ ๋๋ ํ ๋ฆฌ๋ฅผ ๊ฐ์ง.
- master file directory : ์ฌ์ฉ์์ ์์ธ ๋ฒํธ

### โ๏ธ ํธ๋ฆฌ ๊ตฌ์กฐ ๋๋ ํ ๋ฆฌ
![img_4.png](img/img_4.png)
- ํ๋์ ๋ฃจํธ ๋๋ ํ ๋ฆฌ, ์์คํ์ ๋ชจ๋  ํ์ผ์ ๊ณ ์  ๊ฒฝ๋ก๋ฅผ ๊ฐ์ง.
- ํ ๋นํธ๋ก ์ผ๋ฐ ํ์ผ(0), ๋๋ ํ ๋ฆฌ(1)์ ๊ตฌ๋ถ.

### โ๏ธ ๋น์ํ ๊ทธ๋ํ
![img_5.png](img/img_5.png)
- ์ฌ์ดํด์ด ์กด์ฌํ์ง ์๋ ๋๋ ํ ๋ฆฌ ๊ตฌ์กฐ.
- ๋งํฌ๋ผ๊ณ  ๋ถ๋ฆฌ๋ ์๋ก์ด ๋๋ ํ ๋ฆฌ ํญ๋ชฉ์ด ์กด์ฌํ๋ค.
- ์ฐธ์กฐ ๊ณ์๋ฅผ ๋๊ณ  ์ฐธ์กฐ ๊ณ์๊ฐ 0์ผ๋๋ง ์ญ์ ๊ฐ ๊ฐ๋ฅ.


<br><br><br>

> - https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/OS
> - https://dheldh77.tistory.com/entry/Memory-Hierarchy
> - https://oowgnoj.dev/post/os-memory
> - https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ljc8808&logNo=220303236020
> - https://noep.github.io/2016/02/23/10th-filesystem/