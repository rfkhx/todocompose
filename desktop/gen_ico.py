#!/usr/bin/python
# coding: utf-8

# copied from https://zhuanlan.zhihu.com/p/345770773

import os
import sys
# PIL : Python Imaging Library
from PIL import Image


def main():
    # 图标大小
    size = (256,256)

    if len(sys.argv) < 2:
        print("No path to original / hi-res icon provided")
        raise SystemExit

    if len(sys.argv) > 2:
        print("Too many arguments")
        raise SystemExit

    originalPicture = sys.argv[1]
    if not (os.path.isfile(originalPicture)):
        print("There is no such file: {}\n".format(sys.argv[1]))
        raise SystemExit

    fname, ext = os.path.splitext(originalPicture)

    # 打开图片并设置大小
    im = Image.open(originalPicture).resize(size)
    try:
        # 图标文件保存至icon目录
        file = fname + '.ico'
        im.save(file)

        print('{} --> {}'.format(originalPicture, file))
    except IOError:
        print('connot convert :', originalPicture)

if __name__ == '__main__':
    sys.exit(main())

