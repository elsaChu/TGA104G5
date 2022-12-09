window.addEventListener("pageshow", function () {
         console.log("in load");
        // document.getElementsByTagName("form").reset();
        document.getElementById("formName").reset();
      });

      let getInImg = document.getElementsByClassName("prodIMG");
       console.log(getInImg);
       console.log(getInImg.length);
      for (let i = 0; i < getInImg.length; i++) {
        let get_one = getInImg[i];
         console.log(get_one);
        get_one.addEventListener("change", function (e) {
             console.log("change on");
          //   console.log(e.target);
          //清除預覽圖片
          let the_ul = document.getElementsByClassName("picture_list")[i];
          the_ul.innerHTML = "";
          //read file
          let reader = new FileReader();
             console.log(this.files[0]);
          if (this.files.length !== 0) {
            reader.readAsDataURL(this.files[0]);
          }
          reader.addEventListener("load", function () {
             console.log("load on");
            // console.log(this.result);
            //put img in ul
            let li_el = `
				<li>
					<img src="${this.result}" class="preview">
				</li>	
			`;
            let getUL = document.getElementsByClassName("picture_list")[i];
             console.log(getUL);
            getUL.insertAdjacentHTML("beforeend", li_el);
          });
        });
      }
      // }