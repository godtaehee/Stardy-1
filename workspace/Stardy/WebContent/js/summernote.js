(() => {
        const boldBtn = document.querySelector('.bold-btn');
        const styleBtn = document.querySelector('.style-btn');
        const styleList = document.querySelector('.style-list');
        const textContainer = document.querySelector('.txt-con');

        boldBtn.addEventListener('click', bold);

        window.addEventListener('click', e => {
            if(e.target !== styleBtn) {
                if(styleList.classList.contains('show')){
                    styleList.classList.remove('show');
                    styleList.classList.add('hide');
                }
            }
        })

        let loc = 0;

        // textContainer.addEventListener('keydown', (e) => {
        //
        //     console.log(window.selectionStart);
        //     textContainer.innerHTML = `<p>${textContainer.innerText}</p>`;
        //     window.selectionStart = textContainer.innerHTML.length;
        // })


        /* style-Btn */
        styleBtn.addEventListener('click',() => {
            if(styleList.classList.contains('hide')){
                styleList.classList.remove('hide');
                styleList.classList.add('show');
            }else if(styleList.classList.contains('show')){
                styleList.classList.remove('show');
                styleList.classList.add('hide');
            }
        });

        styleList.addEventListener('click', (e) => {
            let outer = window.getSelection().getRangeAt(0).startContainer.parentElement;

            const dataVal = e.target.parentNode.dataset.value;
            const element = document.createElement(dataVal);

            console.log("첫번째 : " + outer.innerHTML.charAt(1));

            if(outer.innerHTML.charAt(1) !== 'p')
                outer.innerHTML = `<p>${outer.innerText}</p>`;
            // console.log(outer.innerHTML);

            console.log("두번째 : " + outer.innerHTML.charAt(1));

            if(outer.innerHTML.charAt(1) === 'p') {
                console.log("들어옴");
                // console.log(outer.innerHTML, outer.innerText);
                outer.innerHTML =  `<${dataVal}>${outer.innerText}</${dataVal}>`;
                return;
            }
            window.getSelection().getRangeAt(0).surroundContents(element);
        });


        function bold(){
            const boldElem = document.createElement('b');
            window.getSelection().getRangeAt(0).surroundContents(boldElem);
        }
    }
)();