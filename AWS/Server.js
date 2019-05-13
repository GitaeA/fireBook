// module
var express = require('express');
var app = express();
var pg = require('pg');
var child_process = require('child_process');
var exec = child_process.exec;
var cmd = "python3 pytest.py 1776"

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
            console.log('/python test');
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