from bilibili_api import video, sync
from java import jclass
import json


class BarrageInfo:
    JavaBean = jclass("com.aceli.bilibililuckdraw.bean.JsonBean")
    infoJson = JavaBean()


class Barrage:
    def __init__(self, barrageList):
        self.barrageList = barrageList


def getBarrageInfo(parameter):
    array = parameter.split(',')
    barrage = []
    for aid in array:
        v = video.Video(bvid=aid)
        dms = sync(v.get_danmakus(0))
        for dm in dms:
            barrage.append(dm.text)

    b = Barrage(barrage)
    BarrageInfo.infoJson.setJsonData(json.dumps(obj2dict(b)))


def obj2dict(obj):
    d = {}
    d['__class__'] = obj.__class__.__name__
    d['__module__'] = obj.__module__
    d.update(obj.__dict__)
    return d


def getJson():
    return BarrageInfo.infoJson


def init(parameter):
    getBarrageInfo(parameter)
