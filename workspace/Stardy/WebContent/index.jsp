<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>

        @keyframes cursor {
            0% { border-right: 1px solid #fff}
            50% { border-right: 1px solid #000}
            100% { border-right: 1px solid #fff}
        }
        .go-btn {
            background-image: url("img/home.png");
            background-repeat: no-repeat;
            background-size: cover;
            width: 64px;
            height: 64px;
            border: 0;
            position: absolute;
            right: 10px;
            top: 10px;
            border-radius: 50%;
        }

        .typing ul li {
            position: absolute;
            font-size: 2rem;
            color: white;
            text-shadow: 2px 2px 2px gray;
        }

        .typing ul li.on {
            display: inline-block;
            animation: cursor 0.3s infinite;
        }

        .typing ul li {
            display: block;
        }

        .fake {
            display: inline-block;
            font-size: 2rem;
        }

        .go-btn a {
            color: transparent;
        }

    </style>
</head>
<body style="position: fixed; left: 0px; right: 0px; top: 0px; bottom: 0px; overflow: hidden; margin: 0; padding: 0; height: 100%">
    <canvas id="canvas" style="width: 100%; height: 100%; padding: 0;margin: 0;"></canvas>
    <button type="button" class="go-btn">
        <a href="index2.jsp">이동하기</a>
    </button>
    <div class="typing">
        <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>

    <div class="fake" >


        <ul>
            <li>안녕하세요! 정다겸입니다!</li>
        </ul>
    </div>

    <script>

        // Typing
        let typingList = ["안녕하세요! 정다겸입니다!",
            "안녕하세요! 김지호입니다!",
            "안녕하세요! 임수영입니다!",
            "안녕하세요! 김태희입니다!"];

        let isTyping = false;
        let typingIdx = 0;
        let liIndex = 0;
        let liLength = document.querySelectorAll('.typing ul li').length;
        let fake = document.querySelector('.fake ul li');
        let tyInt;
        let liList = document.querySelectorAll('.typing ul li');


        let tmpTypingTxt = [];
        let typingTxt;
        for(let i = 0; i < typingList.length; i++) {
            tmpTypingTxt.push(typingList[i].split(""));
        }

        typingTxt = tmpTypingTxt[liIndex];

        if(!isTyping) {
            isTyping = true;
            tyInt = setInterval(goTyping, 100);
        }

        function goTyping() {
            let typing = document.querySelectorAll('.typing ul li');

            for(let i = 0; i < typing.length; i++){
                typing[i].classList.remove('on');
            }
            typing[liIndex].classList.add('on');
            if(typingIdx < typingTxt.length) {
                typing[liIndex].append(typingTxt[typingIdx++]);
            }else{
                if(liIndex < liLength-1) {
                    liIndex++;
                    typingIdx = 0;
                    isTyping = false;
                     typingTxt = tmpTypingTxt[liIndex];
                    clearInterval(tyInt);
                    setTimeout(function () {
                        tyInt = setInterval(goTyping,100);
                    },1000);
                } else if(liIndex == liLength - 1) {
                    clearInterval(tyInt);
                }
            }
        }

        // Canvas

        const canvas = document.querySelector('#canvas');
        const c = canvas.getContext("2d");

        let img = new Image();
        img.src = "img/landinglogo.png";

        c.textAlign = 'center';
        c.fillText('비디오 로딩 중..', 300, 200);



        function render() {
            c.drawImage(videoElem, canvas.clientWidth/2 - 300, canvas.clientHeight/2 - 200, 600, 400);
            requestAnimationFrame(render);
        }
        let w;
        let h;
        const setCanvasExtents = () => {
            for(let i = 0; i < liList.length; i++) {
                liList[i].style.left = `${document.body.clientWidth/2 - fake.clientWidth/2}px`;
                liList[i].style.top = `${document.body.clientHeight/2 + 40*i}px`;
            }
            w = document.body.clientWidth;
            h = document.body.clientHeight;
            canvas.width = w;
            canvas.height = h;

        }
        setCanvasExtents();
        window.onresize = () => {
            setCanvasExtents();
        }

        const makeStars = (count) => {
            const out = [];
            for(let i = 0; i < count; i++) {
                const s = {
                    x: Math.random()*1600 - 800,
                    y: Math.random()*900 -450,
                    z: Math.random()*1000
                };
                out.push(s);
            }
            return out;
        }
        let stars = makeStars(10000);

        const clear = () => {
            let grd = c.createLinearGradient(0,0,0, canvas.height);
            grd.addColorStop(0, "#131138");
            grd.addColorStop(0.5,"#23196f")
            grd.addColorStop(1, "#131138");
            c.fillStyle = grd;
            c.fillRect(0, 0, canvas.width, canvas.height);
        };

        const putPixel = (x, y, brightness,i) => {
            const r = Math.random() * 255;
            const g = Math.random() * 255;

            const intensity = brightness*255;

            const b = Math.random() * 255;
            // const rgb = `rgb(${r},${g},${b})`;
            const rgb = `rgb(${intensity},${intensity},${intensity}`;

            c.fillStyle = rgb;

            c.beginPath();
            c.arc(x, y, 1, 0, Math.PI * 2);
            c.fill();
            c.closePath();


        };

        const moveStars = (distance) => {
            const count = stars.length;
            for( let i = 0; i < count; i++) {
                const s = stars[i];
                s.z -= distance;
                while(s.z <= 1) {
                    s.z+=1000;
                }
            }
        };


        let prevTime;
        const init = time => {

            prevTime = time;
            requestAnimationFrame(tick);
        }
        requestAnimationFrame(init);

        const tick = time => {
            let elapsed = time - prevTime;
            prevTime = time;
            moveStars(elapsed*0.1);
            clear();
            const cx = w/2;
            const cy = h/2;

            c.drawImage(img, cx - 346, 120, 692, 295);

            const count = stars.length;
            for (var i = 0; i < count; i++) {
                const star = stars[i];
                const x = cx + star.x/(star.z * 0.001);
                const y = cy + star.y/(star.z * 0.001);



                if (x < 0 || x >= w || y < 0 || y >= h){
                    continue;
                }

                const d = (star.z/1000.0);
                const b = 1-d*d;

                putPixel(x, y, b, i);

            }
            requestAnimationFrame(tick);
        };

    </script>
</body>
</html>