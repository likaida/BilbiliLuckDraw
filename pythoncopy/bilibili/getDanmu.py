from bilibili_api import video, sync

v = video.Video(bvid='BV1vQ4y1D7KJ')

dms = sync(v.get_danmakus(0))

for dm in dms:
    print(dm)