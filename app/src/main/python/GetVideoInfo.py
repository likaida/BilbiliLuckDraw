import asyncio
from bilibili_api import video
from java import jclass
import json


class VideoInfo:
    JavaBean = jclass("com.aceli.bilibililuckdraw.bean.JsonBean")
    infoJson = JavaBean()


# "BV1vQ4y1D7KJ"
async def getAudioInfo(parameter):
    # 实例化 Video 类
    v = video.Video(bvid=parameter)
    # 获取信息
    info = await v.get_info()
    # 打印信息
    VideoInfo.infoJson.setJsonData(json.dumps(info))


def getJson():
    return VideoInfo.infoJson


def init(parameter):
    asyncio.get_event_loop().run_until_complete(getAudioInfo(parameter))


