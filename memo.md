## やること

### jsp
- ログイン後ページが日記一覧ページになるようにする。
- 日記一覧ページから日記作成ページへ移動できるようにする。
- 日記作成ページの動作確認。
- 動的院クルードで一覧をユーザページに表示


### Bean
- CreateDiaryBeanを作成する。
- ログイン後ページが自動的に今までの日記データをDBから引っ張ってきて表示するにはどうしたらいいのか。
- auto commitがoffなので確定する機能をつける。
- **loginSubmitBean**でsessionに値入れてる!!!

### test
- servletのtestをmockitoで書く。

### VO
- DiaryVOのcompareToの並び順を変える。


## エラー解決ログ
- java.sql.SQLSyntaxErrorException: INSERT command denied to user 'hoge'@'localhost' for table 'diarycontent' --> 権限エラー grantsしたら直った。
- com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException: Lock wait timeout exceeded; try restarting transaction --> 別端末でmysqlにログインしている状態で変更を加えようとしたので怒られた。
- selectをdbでやるとNullPointExceptionが来る --> DBの値にNullが入っている。
- String型の比較には.equalsを使う。
- [htmlのformタグのname属性について](http://html5.cyberlab.info/elements/forms/form-name.html)これを指定するとformをjsから引っ張れる。
- デザインパターンはこれ?https://qiita.com/takuya-s/items/8898e9bae34acada6480
### loginからの遷移先は/loginになっている --> 遷移先のページに翔べないのはなぜ?
- formのactionには本来は相対パスを設定する。loginを入れた場合には元ページ/loginにとぼうとする。web.xmlにサーブレットに飛ぶように設定することでサーブレットを使ってページ遷移を実現できる。
- サーバーで使用中のサーブレットはmvn compileで編集されないことがある(再現はできなかった)。--> Synchronizedに鳴るまで待つ。
- restart server for debugが使える。



## jsp
- formタグのactionは空にするのが良い。 --> 使ってない?

