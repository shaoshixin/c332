using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using System.Web;
using System.Web.Services;

namespace GreenPictureWebService
{
    /// <summary>
    /// Service1 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://suishoupai/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消注释以下行。
    //[System.Web.Script.Services.ScriptService]
    public class Service1 : System.Web.Services.WebService
    {
        DBOperation dbOperation = new DBOperation();
        [WebMethod]
        public string HelloWorld()
        {
            return "Hello World";
        }
        [WebMethod(Description = "注册信息")]
        public string insertCargoInfo(int Unum, string nickname, string password)
        {
            return dbOperation.insertCargoInfo(Unum, nickname, password);
        }
        [WebMethod(Description = "登录")]
        public string login(int Unum, string password)
        {
            return dbOperation.login(Unum, password);
        }
        [WebMethod(Description = "获取所有信息")]
        public string[] selectAllLocation()
        {
            return dbOperation.selectAllLocation().ToArray();
        }
        [WebMethod(Description = "上传")]
        public string insertInfo(string id, int Tuser, int count, string time, string text, float x, float y)
        {
            return dbOperation.insertInfo(id, Tuser, count, time, text, x, y);
        }
        [WebMethod(Description = "删除一条")]
        public string deleteInfo(string id)
        {
            return dbOperation.deleteInfo(id);
        }
        [WebMethod(Description = "更新点赞")]
        public String updateInfo(string id)
        {
            return dbOperation.updateInfo(id);
        }
        [WebMethod(Description = "取消点赞")]
        public String updateInfo1(string id)
        {
            return dbOperation.updateInfo1(id);
        }
        [WebMethod(Description = "上传图片文件")]

        public string FileUploadImage(string ImagefileName, string image)
        {
            string ImageBasePath = "./images/";
            string mess = "";
            byte[] byteFileStream = Convert.FromBase64String(image);
            try
            {
                string filePath = Server.MapPath(ImageBasePath + ImagefileName + ".jpg");
                if (!Directory.Exists(Path.GetDirectoryName(filePath)))
                    Directory.CreateDirectory(Path.GetDirectoryName(filePath));
                FileStream fs = new FileStream(filePath, FileMode.OpenOrCreate, FileAccess.Write);
                BinaryWriter w = new BinaryWriter(fs);
                w.Write(byteFileStream);
                w.Flush();
                fs.Close();
            }
            catch (Exception eX)
            {
                mess = eX.Message;
            }
            if (mess != "")
            {
                return mess;
            }
            else
            {
                return "成功";
            }
        }

        [WebMethod(Description = "上传评论")]
        public string insertTalk(String id, int talker, string talk)
        {
            return dbOperation.insertTalk(id, talker, talk);
        }

        [WebMethod(Description = "获取评论")]
        public string[] getTalk(String id)
        {
            return dbOperation.getTalk(id).ToArray();
        }
    }
}