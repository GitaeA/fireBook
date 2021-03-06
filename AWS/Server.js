// module
var express = require('express');
var app = express();
var pg = require('pg');
var child_process = require('child_process');
var exec = child_process.exec;
var cmd = "python3 pytest.py"

// DB: AWS RDS connection
var db = new pg.Pool({
        user: 'gitae',
        host: 'bookingdata.c7bt9br99pg3.ap-northeast-2.rds.amazonaws.com',
        database: 'Booking',
        password: 'booking123',
        port: '5432',
});

//server running
app.listen(4000, function (err, res) {
        console.log("SERVER RUNNING")
});


//python start
app.get('/test', (req, res) => {
        exec(cmd, (err, stdout, stderr) => {
                if(err){
                        console.error(err);
                        return;
                }
                console.log('/rtest');
                res.send(stdout);
        });
});

// ======================= DB connect ==========================
app.get('/ing', (req, res) => {
        db.connect(function (err, client, done) {
                client.query(
                        'select * from test '
                        , function (err, result) {
                                done();
                                res.send(result.rows);
                                console.log('/ing');   //test ��
                        });
        });

});

app.get('/jointest', (req, res) => {
        db.connect(function (err, client, done) {
                client.query(
                        'insert into test_user values(\'' + req.query.id + '\',\'' + req.query.pw + '\',\'' + req.query.nick + '\',\'' + req.query.phone + '\') '
                        , function (err, result) {
                                done();
                                res.send(result.rows);
                                console.log('jointest');   //join
                        });
        });

});

app.get('/logintest', (req, res) => {
        db.connect(function (err, client, done) {
                client.query(
                        'select pw,phone,name from test_user where id=\'' + req.query.id + '\' '
                        , function (err, result) {
                                done();
                                res.send(result.rows);
                                console.log('logintest');   //login
                        });
        });

});

app.get('/search', (req, res) => {
        db.connect(function (err, client, done) {
                client.query(
                         "select title, image_url, publisher, authors, price, state, deliver from books where title::text like '%" +req.query.search+"%'"
                        , function(err, result) {
                                done();
                                res.send(result.rows);
                                console.log('search'); // search
                        });
        });
});

app.get('/sale', (req, res) => {
	db.connect(function (err, client, done) {
		client.query(
			'insert into books values(' + req.query.book_id + ' ,' + req.query.isbn + ' ,\'' + req.query.authors + '\' ,\'' + req.query.title + '\' ,\'' + req.query.language + '\' '
			+ ' ,\'' + req.query.image + '\' ,\'' + req.query.memo + '\' ,\'' + req.query.price + '\' ,\'' + req.query.publish + '\',' + req.query.state + ',' + req.query.deliver + ',\'' + req.query.sid + '\',\'' + req.query.phone + '\') '
			, function (err, result) {
				done();
				res.send(result.rows);
				console.log('sale');   //join
			});
	});

});

app.get('/rate', (req, res) => {
	db.connect(function (err, client, done) {
		client.query(
			'insert into ratings values(' + req.query.book_id + ', ' + req.query.user_id + ' ,' + req.query.rating +') '
			
			, function (err, result) {
				done();
				res.send(result.rows);
				console.log('rate');   //join
			});
	});

});

app.get('/jang', (req, res) => {
        db.connect(function (err, client, done) {
           client.query(
              'insert into jang2 values(\'' + req.query.title + '\' ,\'' + req.query.image + '\',\'' + req.query.phone + '\' ,' + req.query.price + ',\'' + req.query.user_id + '\') '
     
              , function (err, result) {
                 done();
                 res.send(result.rows);
                 console.log('jang');   
              });
        });
     
     });
     
     
     app.get('/mybuy', (req, res) => {  //장바구니
        obj = new Object();
        udm.connect(function (err, client, done) {
           client.query(
              'select jtitle,jimage,jphone,jprice from jang2 where juser_id=\'' + req.query.my_id + '\''
              
     
              , function (err, result) {
                 done();
                 obj.code = "mybuy";
                 obj.data = result.rows;
                 res.send(JSON.stringify(obj));
                 console.log('/mybuy');   
              });
        });
     
     });
     
     app.get('/mysale', (req, res) => {   //내가 판매 
        obj = new Object();
        udm.connect(function (err, client, done) {
           client.query(
              'select title,image_url,price,authors from books where user_id=\'' + req.query.my_id + '\' ' 
              
     
              , function (err, result) {
                 done();
                 obj.code = "mysale";
                 obj.data = result.rows;
                 res.send(JSON.stringify(obj));
                 console.log('/mysale');   
              });
        });
     
     });


