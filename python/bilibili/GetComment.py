from bilibili_api import comment, sync
import json


async def main():
    # 存储评论
    comments = []
    # 页码
    page = 1
    # 当前已获取数量
    count = 0
    file_name = 'comment.json'

    while True:
        # 获取评论
        c = await comment.get_comments(718610052, comment.ResourceType.VIDEO, page)
        # 存储评论
        comments.extend(c['replies'])
        # 增加已获取数量
        count += c['page']['size']
        # 增加页码
        page += 1

        if count >= c['page']['count']:
            # 当前已获取数量已达到评论总数，跳出循环
            break
    print(f"{{")
    # 打印评论
    for cmt in comments:
        print(f"\"{cmt['member']['uname']}_{(cmt['content']['message'])}\",")
    print(f"}}")
    # 打印评论总数
    print(f"\n\n共有 {count} 条评论（不含子评论）")

    with open(file_name, 'w') as file_object:
        json.dump(comments, file_object)


sync(main())
