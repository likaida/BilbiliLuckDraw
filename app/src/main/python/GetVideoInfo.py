import asyncio
from bilibili_api import video
from java import jclass

class VideoInfo:
    JavaBean = jclass("com.aceli.bilibililuckdraw.bean.VideoInfoBean")
    info = JavaBean()
    aid=0


# "BV1vQ4y1D7KJ"
async def getAudioInfo(parameter):
    # 实例化 Video 类
    v = video.Video(bvid=parameter)
    # 获取信息
    info = await v.get_info()
    # 打印信息
    VideoInfo.info.setVideoId(info.get("aid"))


def getAid():
    return VideoInfo.info


def init(parameter):
    asyncio.get_event_loop().run_until_complete(getAudioInfo(parameter))


