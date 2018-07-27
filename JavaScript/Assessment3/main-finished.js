// setup canvas

var canvas = document.querySelector('canvas');
var ctx = canvas.getContext('2d');
var para = document.querySelector("p");

var width = canvas.width = window.innerWidth;
var height = canvas.height = window.innerHeight;
var numOfBalls = 0;
// function to generate random number

function random(min,max) {
  var num = Math.floor(Math.random()*(max-min)) + min;
  return num;
}

// define Ball constructor

function Shape(x, y, velX, velY, exists) {
  this.x = x;
  this.y = y;
  this.velX = velX;
  this.velY = velY;
  this.exists = exists;
}

function Ball(x, y, velX, velY, exists, color, size){
    Shape.call(this, x, y, velX, velY, exists);
    this.color = color;
    this.size = size;
}

Ball.prototype = Object.create(Shape.prototype);
Ball.prototype.constructor = Ball;

// define ball draw method

Ball.prototype.draw = function() {
  ctx.beginPath();
  ctx.fillStyle = this.color;
  ctx.arc(this.x, this.y, this.size, 0, 2 * Math.PI);
  ctx.fill();
};

// define ball update method

Ball.prototype.update = function() {
  if((this.x + this.size) >= width) {
    this.velX = -(this.velX);
  }

  if((this.x - this.size) <= 0) {
    this.velX = -(this.velX);
  }

  if((this.y + this.size) >= height) {
    this.velY = -(this.velY);
  }

  if((this.y - this.size) <= 0) {
    this.velY = -(this.velY);
  }

  this.x += this.velX;
  this.y += this.velY;
};

// define ball collision detection

Ball.prototype.collisionDetect = function() {
  for(var j = 0; j < balls.length; j++) {
    if(!(this === balls[j])) {
      var dx = this.x - balls[j].x;
      var dy = this.y - balls[j].y;
      var distance = Math.sqrt(dx * dx + dy * dy);

      if (distance < this.size + balls[j].size && balls[j].exists) {
        balls[j].color = this.color = 'rgb(' + random(0,255) + ',' + random(0,255) + ',' + random(0,255) +')';
      }
    }
  }
};

function EvilCircle(x, y, exists){
    Shape.call(this, x, y, 20, 20, exists);
    this.color = "white";
    this.size = 10;
}

EvilCircle.prototype = Object.create(Shape.prototype);
EvilCircle.prototype.constructor = EvilCircle;

EvilCircle.prototype.draw = function(){
  ctx.beginPath();
  ctx.strokeStyle = this.color;
  ctx.lineWidth = 3;
  ctx.arc(this.x, this.y, this.size, 0, 2 * Math.PI);
  ctx.stroke();
};

EvilCircle.prototype.checkBounds = function(){
    // right side of the screen
    if((this.x + this.size) >= width) {
        this.x -= this.size;
    }
    // left side of the screen
    if((this.x - this.size) <= 0) {
        this.x += this.size;
    }
    // top of the screen
    if((this.y + this.size) >= height) {
        this.y -= this.size;
    }
    // bottom of the screen
    if((this.y - this.size) <= 0) {
        this.y += this.size;
    }
};

EvilCircle.prototype.setControls = function(){
    // _this is used for keeping the outer constructor scope from the event handler.
    var _this = this;
    window.onkeydown = function(e) {
        // Keycode of A
        if (e.keyCode === 65) {
            _this.x -= _this.velX;
        } 
        // Keycode of D
        else if (e.keyCode === 68) {
            _this.x += _this.velX;
        }
        // Keycode of W
        else if (e.keyCode === 87) {
            _this.y -= _this.velY;
        } 
        // Keycode of S
        else if (e.keyCode === 83) {
            _this.y += _this.velY;
        }
    };
};

EvilCircle.prototype.collisionDetect = function(){
    for(var j = 0; j < balls.length; j++) {
        if(balls[j].exists) {
            var dx = this.x - balls[j].x;
            var dy = this.y - balls[j].y;
            var distance = Math.sqrt(dx * dx + dy * dy);
        
            if (distance < this.size + balls[j].size) {
                balls[j].exists = false;
                numOfBalls--;
                para.textContent = "Ball count: " + numOfBalls;
            }
        }
    }
};

// define array to store balls

var balls = [];
var evilBall = new EvilCircle(random(10, width), random(10, height), true);
evilBall.setControls();
// define loop that keeps drawing the scene constantly
function loop() {
    ctx.fillStyle = 'rgba(0,0,0,0.25)';
    ctx.fillRect(0,0,width,height);
    
    while(balls.length < 25) {
        var size = random(10,20);
        var ball = new Ball(
            // ball position always drawn at least one ball width
            // away from the adge of the canvas, to avoid drawing errors
            random(0 + size,width - size),
            random(0 + size,height - size),
            random(-7,7),
            random(-7,7),
            true,
            'rgb(' + random(0,255) + ',' + random(0,255) + ',' + random(0,255) +')',
            size
        );
        balls.push(ball);
        numOfBalls++;
        para.textContent = "Ball count: " + numOfBalls;
    }
    for(var i = 0; i < balls.length; i++) {
        if(balls[i].exists){
            balls[i].draw();
            balls[i].update();
            balls[i].collisionDetect();
        }
    }

    evilBall.draw();
    evilBall.checkBounds();
    evilBall.collisionDetect();
    requestAnimationFrame(loop);
}



loop();