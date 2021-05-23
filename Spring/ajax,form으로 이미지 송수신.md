

### Html form
````html
<form id="input-image" method="post" enctype="multipart/form-data">
    <input type="file" name="image" >
    <button type="button" class="btn btn-success" id="btn-upload">파일 업로드</button>
</form>
````
:: 간단하게 파일의 추가와 업로드 버튼을 구현, file imput의 속성을 multiple을 주면 여러개의 파일도 업로드 가능하다.

### Ajax
````javascript
 upload : function () {
        var formData = new FormData($('#input-image')[0]);
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: '/api/input',
            data: formData,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            dataType: 'text',
            success: function (result) {
                alert("파일 업로드 완료.");
                window.location.href = "/wait-page";
            },
            error: function (e) {
                alert("파일 업로드 실패: " + JSON.stringify(e));
            }
        });
    }
````
:: Post 방식으로 전송. 처음에는 dataType을 json으로 했었는데, 컨트롤러에서 데이터는 수신하여
status 200 code를 받지만 반환 형식이 맞지않아 error가 발생하였다. 때문에 리턴 타입에 맞춰 text로 변경.

### Controller
````java
@PostMapping("/api/input")
    public @ResponseBody void inputSaveTest( @RequestParam("image")MultipartFile image) throws IOException {
        String filePath="/Users/kimtaejun/Desktop/Capstone/pidetection/src/main/java/com/back/pidetection/web/";

        String fileName = image.getOriginalFilename();
        System.out.println("파일 이름 : "+fileName);
        byte[] im = image.getBytes();
        long fileSize = (long)im.length;
        System.out.println("파일 크기 : "+im.length);

        try{
            image.transferTo(new File(filePath + fileName));

        } catch (IOException e) {
            System.out.println("사용자 이미지 저장 실패.");
            e.printStackTrace();
        }
        System.out.println("사용자 이미지 저장 성공.");

    }
````
:: @ResponseBody 어노테이션을 사용하지 않으면 ajax에서 응답을 받지 못해 error로 판단한다.     
:: RequestParam에서 아까 input의 name으로 입력하였던 "image"를 설정하고, MultipartFile 형식으로 데이터를 수신한다.     
:: 이름, 크기 등의 정보를 얻고 transferTo 메소드를 이용하여 파일을 저장한다.