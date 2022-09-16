import os
import pdfplumber
import pymysql
import re

# 年报解压后存放的文件夹 本地
# total_folder_path = r'E:\WorkPlace\文章检索\src'
# mysql_conf = {
#     "host": 'localhost',
#     "port": 3306,
#     "user": 'root',
#     "password": 'root',
#     "db": 'articlesearch',
#     "charset": "utf8mb4",
#     "cursorclass": pymysql.cursors.DictCursor
# }


# 服务器端年报文件夹路径
total_folder_path = r'/home/buiz/ReportSrc'
mysql_conf = {
    "host": 'localhost',
    "port": 3306,
    "user": 'articlesearch',
    "password": 'root',
    "db": 'articlesearch',
    "charset": "utf8mb4",
    "cursorclass": pymysql.cursors.DictCursor
}

# 打开数据库连接
db = pymysql.connect(**mysql_conf)
# 使用 cursor() 方法创建一个游标对象 cursor
cursor = db.cursor()
insert_sql = "insert into report(stockCode, companyName, Section, year, publicDate, fileName, pageNumber, fileSource, content) values(%s, %s, %s, %s, %s, %s, %s, %s, %s)"
insert_section_sql = "insert into section(sectionName) values(%s)"
query_sql = "select id FROM report WHERE fileName = %s"

for root, dirs, files in os.walk(total_folder_path):
    #  AR
    for secondDir in dirs:
        second_folder_path = os.path.join(root, secondDir)
        print("已进入二级文件夹 " + second_folder_path)
        #  2001
        for root2, dirs2, files2 in os.walk(second_folder_path):
            for thirdDir in dirs2:
                third_folder_path = os.path.join(root2, thirdDir)
                print("已进入三级文件夹 " + third_folder_path)
                if secondDir == "AR":
                    for root3, dirs3, files3 in os.walk(third_folder_path):
                        for moduleDir in dirs3:
                            theme_folder_path = os.path.join(root3, moduleDir)
                            print("已进入版块文件夹 " + theme_folder_path)

                            try:
                                # 添加新的版块
                                cursor.execute(insert_section_sql, moduleDir)
                                db.commit()
                            except:
                                print("版块插入失败,请查检代码")
                                raise
                                db.close()

                            for file in os.listdir(theme_folder_path):
                                article = ""
                                file_name = os.path.splitext(file)[0]
                                extension_name = os.path.splitext(file)[1]
                                # 本地路径
                                # file_path = theme_folder_path + '\\' + file_name + extension_name
                                # 服务器路径
                                file_path = theme_folder_path + '/' + file_name + extension_name
                                print("正在处理文件 " + file_path)

                                # 文件名划分
                                # detail = file_name.split()
                                # # detail[2] = detail[2].split("-")[0]
                                # year = re.search(r"\d{4}", detail[3]).group(0)
                                # print(detail[0], detail[1], detail[2], detail[3])
                                detail = re.split('[-：_]', file_name)

                                # 判断来源
                                # fileSource = ""
                                # if "年度报告" in file_name or "年报" in file_name:
                                #     fileSource = "年报"
                                # elif "社会责任报告" in file_name:
                                #     fileSource = "社会责任报告"

                                # 查询文件是否已经记录过
                                if cursor.execute(query_sql, file_name):
                                    print(file_name + "已经记录过")
                                elif cursor.execute(query_sql, file_name + extension_name):
                                    print(file_name + extension_name + "已经记录过2021")
                                else:
                                    pageNumber = 0

                                    try:
                                        with pdfplumber.open(file_path) as pdf:
                                            for page in pdf.pages:
                                                article += page.extract_text()
                                                pageNumber += 1
                                        print(pageNumber)
                                    except:
                                        print("出现PDF读取错误!!!  文件名: " + file_name)

                                    try:
                                        # if detail[0] == "2021":
                                        # 执行sql语句
                                        cursor.execute(insert_sql, (
                                            detail[1], detail[2], moduleDir, detail[0], '',
                                            file_name + extension_name, pageNumber,
                                            "年报",
                                            article))
                                        # 提交到数据库执行
                                        db.commit()
                                        # else:
                                        #     # 执行sql语句
                                        #     cursor.execute(insert_sql, (
                                        #         detail[1], detail[2], moduleDir, detail[0], '', file_name, pageNumber,
                                        #         "年报",
                                        #         article))
                                        #     # 提交到数据库执行
                                        #     db.commit()

                                    except:
                                        print("数据插入失败,请查检try语句里的代码")
                                        raise
                                        db.close()



                elif secondDir == "ESG":
                    for moduleDir in dirs2:
                        theme_folder_path = os.path.join(root2, moduleDir)
                        print("已进入版块文件夹 " + theme_folder_path)

                        try:
                            # 添加新的版块
                            cursor.execute(insert_section_sql, moduleDir)
                            db.commit()
                        except:
                            print("版块插入失败,请查检代码")
                            raise
                            db.close()

                        for file in os.listdir(theme_folder_path):
                            article = ""
                            file_name = os.path.splitext(file)[0]
                            extension_name = os.path.splitext(file)[1]
                            # 本地路径
                            # file_path = theme_folder_path + '\\' + file_name + extension_name
                            # 服务器路径
                            file_path = theme_folder_path + '/' + file_name + extension_name
                            print("正在处理文件 " + file_path)

                            # 文件名划分
                            # detail = file_name.split()
                            # # detail[2] = detail[2].split("-")[0]
                            # year = re.search(r"\d{4}", detail[3]).group(0)
                            # print(detail[0], detail[1], detail[2], detail[3])
                            detail = re.split('[-：_]', file_name)

                            # 判断来源
                            # fileSource = ""
                            # if "年度报告" in file_name or "年报" in file_name:
                            #     fileSource = "年报"
                            # elif "社会责任报告" in file_name:
                            #     fileSource = "社会责任报告"

                            # 查询文件是否已经记录过
                            if cursor.execute(query_sql, file_name):
                                print(file_name + "已经记录过")
                            elif cursor.execute(query_sql, file_name + extension_name):
                                print(file_name + extension_name + "已经记录过2021")
                            else:
                                pageNumber = 0

                                try:
                                    with pdfplumber.open(file_path) as pdf:
                                        for page in pdf.pages:
                                            article += page.extract_text()
                                            pageNumber += 1
                                    print(pageNumber)
                                except:
                                    print("出现PDF读取错误!!!  文件名: " + file_name)

                                try:
                                    # if detail[0] == "2021":
                                    # 执行sql语句
                                    cursor.execute(insert_sql, (
                                        detail[1], detail[2], moduleDir, detail[0], '', file_name + extension_name,
                                        pageNumber,
                                        "社会责任报告",
                                        article))
                                    # 提交到数据库执行
                                    db.commit()
                                    # else:
                                    #     # 执行sql语句
                                    #     cursor.execute(insert_sql, (
                                    #         detail[1], detail[2], moduleDir, detail[0], '', file_name, pageNumber,
                                    #         "社会责任报告",
                                    #         article))
                                    #     # 提交到数据库执行
                                    #     db.commit()

                                except:
                                    print("数据插入失败,请查检try语句里的代码")
                                    raise
                                    db.close()
# 关闭数据库连接
db.close()
