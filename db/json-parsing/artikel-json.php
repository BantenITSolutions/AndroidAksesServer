<?php
include("koneksi.php");
$q = mysql_query('
SELECT * FROM tbl_content
');
$v = '{"artikel" : [';
while($r=mysql_fetch_array($q))
{
	$ob = array('"','<br>','</br>');
	if(strlen($v)<15)
	{
		$v .= '{"id" : "'.$r['id_content'].'", "judul" : "'.str_replace($ob,' ',strip_tags($r["judul"])).'", "content" : "'.str_replace($ob,' ',strip_tags($r["content"])).'"}';
	}
	else
	{
		$v .= ',{"id" : "'.$r['id_content'].'", "judul" : "'.str_replace($ob,' ',strip_tags($r["judul"])).'", "content" : "'.str_replace($ob,' ',strip_tags($r["content"])).'"}';
	}
}
$v .= ']}';
echo $v;
?>