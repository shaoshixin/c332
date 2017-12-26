using System;
using System.Data;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Xml.Linq;
using System.Data.SqlClient;
using System.Text.RegularExpressions;
using System.Collections;
using System.Collections.Generic;

namespace GreenPictureWebService
{
    /// <summary>  
    /// 一个操作数据库的类，所有对SQLServer的操作都写在这个类中，使用的时候实例化一个然后直接调用就可以  
    /// </summary>  
    public class DBOperation : IDisposable
    {
        public static SqlConnection sqlCon;  //用于连接数据库  

        //将下面的引号之间的内容换成上面记录下的属性中的连接字符串  
        private String ConServerStr = @"Data Source=localhost;Initial Catalog=Esri;Integrated Security=True";

        //默认构造函数  
        public DBOperation()
        {
            if (sqlCon == null)
            {
                sqlCon = new SqlConnection();
                sqlCon.ConnectionString = ConServerStr;
                sqlCon.Open();
            }
        }

        //关闭/销毁函数，相当于Close()  
        public void Dispose()
        {
            if (sqlCon != null)
            {
                sqlCon.Close();
                sqlCon = null;
            }
        }
        //注册
        public string insertCargoInfo(int Unum, string nickname, string password)//,string userName,string pictureId,string text,string location
        {
            try
            {
                string sql = "insert into Users  (Unum,nickname,password) values ('" + Unum + "','" + nickname + "','" + password + "')";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();
                return "注册成功";
            }
            catch (Exception)
            {
                return "注册失败";
            }
        }
        // 登录
        public string login(int Unum, string password)
        {

            string sql = "select * from Users where Unum = '" + Unum + "'and password ='" + password + "' ";
            SqlCommand cmd = new SqlCommand(sql, sqlCon);

            if (cmd.ExecuteScalar() != null)
            {
                return "登录成功";
            }
            else
            {
                return "登录失败";
            }



        }
        //获取所有位置信息
        public List<string> selectAllLocation()
        {
            List<string> list = new List<string>();

            try
            {
                string sql = "select * from Info";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    //将结果集信息添加到返回向量中  
                    list.Add(reader[0].ToString());
                    list.Add(reader[1].ToString());
                    list.Add(reader[2].ToString());
                    list.Add(reader[3].ToString());
                    list.Add(reader[4].ToString());
                    list.Add(reader[5].ToString());
                    list.Add(reader[6].ToString());

                }

                reader.Close();
                cmd.Dispose();

            }
            catch (Exception)
            {

            }
            return list;
        }
        //上传
        public string insertInfo(string id, int Tuser, int count, string time, string text, float x, float y)
        {
            try
            {
                string sql = "insert into Info  (id,Tuser,count,time,text,x,y) values ('" + id + "'," + Tuser + "," + count + ",'" + time + "','" + text + "'," + x + "," + y + ") ";

                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();
                return (Tuser.ToString());
            }
            catch (Exception)
            {
                return "上传失败";
            }

        }
        /// <summary>  
        /// 删除一条信息  
        /// </summary>  

        public string deleteInfo(string id)
        {
            try
            {
                string sql = "delete from Info where id='" + id + "'";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();

                return "删除成功";
            }
            catch (Exception)
            {
                return "删除失败";
            }
        }
        //修改点赞后信息
        public String updateInfo(string id)
        {
            try
            {
                string sql = "update Info set count=count+1 where id = '" + id + "'";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();

                return "点赞";
            }
            catch (Exception)
            {
                return "点赞失败";
            }
        }
        public String updateInfo1(string id)
        {
            try
            {
                string sql = "update Info set count=count-1 where id = '" + id + "'";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();

                return "点赞取消";
            }
            catch (Exception)
            {
                return "点赞取消失败";
            }
        }

        // 上传 评论
        public string insertTalk(String id, int talker, string talk)
        {
            try
            {
                string sql = "insert into Talk  (id,talker,talk) values ('" + id + "'," + talker + ",'" + talk + "')";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                cmd.ExecuteNonQuery();
                cmd.Dispose();
                return "评论成功";
            }
            catch (Exception)
            {
                return "评论失败";
            }
        }
        //获取评论
        public List<string> getTalk(string id)
        {
            List<string> list = new List<string>();

            try
            {
                string sql = "select * from Talk where id = '" + id + "'";
                SqlCommand cmd = new SqlCommand(sql, sqlCon);
                SqlDataReader reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    //将结果集信息添加到返回向量中  
                  //  list.Add(reader[0].ToString());
                    list.Add(reader[1].ToString());
                    list.Add(reader[2].ToString());
                }

                reader.Close();
                cmd.Dispose();

            }
            catch (Exception)
            {

            }
            return list;
        }
    }
}