<?php
$kd = $_GET['kode'];
include("koneksi.php");
$q = mysql_query('SELECT * FROM tbl_content where id_content="'.$kd.'"');
$v = '{"artikel" : [';
while($r=mysql_fetch_array($q))
{
	$ob = array('"','<br>','</br>');
		$v .= '{"id" : "'.$r['id_content'].'", "judul" : "'.str_replace($ob,' ',strip_tags($r["judul"])).'", "content" : "'.str_replace($ob,' ',strip_tags($r["content"])).'", "tanggal" : "'.str_replace($ob,' ',strip_tags($r["tanggal"])).'", "waktu" : "'.str_replace($ob,' ',strip_tags($r["waktu"])).'", "penulis" : "'.str_replace($ob,' ',strip_tags($r["penulis"])).'"}';

}
$v .= ']}';
echo $v;
?>