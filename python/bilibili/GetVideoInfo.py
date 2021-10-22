import asyncio
from bilibili_api import video
import json


async def main():
    # 实例化 Video 类
    v = video.Video(bvid="BV1vQ4y1D7KJ")
    # 获取信息
    info = await v.get_info()
    # 打印信息
    print(info)
    file_name = 'video_info.json'
    with open(file_name, 'w') as file_object:
        json.dump(info, file_object)


if __name__ == '__main__':
    asyncio.get_event_loop().run_until_complete(main())
