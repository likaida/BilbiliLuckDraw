from bilibili_api import comment, sync
from java import jclass
import json


class CommentList:
    JavaBean = jclass("com.aceli.bilibililuckdraw.bean.JsonBean")
    infoJson = JavaBean()


async def getComment(parameter):
    # 存储评论
    comments = []
    # 页码
    page = 1
    # 当前已获取数量
    count = 0
    array = parameter.split(',')
    for aid in array:  # 第二个实例
        while True:
            # 获取评论
            c = await comment.get_comments(aid, comment.ResourceType.VIDEO, page)
            # 存储评论
            comments.extend(c['replies'])
            # 增加已获取数量
            count += c['page']['size']
            # 增加页码
            page += 1
            if count >= c['page']['count']:
                # 当前已获取数量已达到评论总数，跳出循环
                break
    CommentList.infoJson.setJsonData(json.dumps(comments))


def getJson():
    return CommentList.infoJson


def init(parameter):
    sync(getComment(parameter))
