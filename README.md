# TFC BestFormula

## 简介

TFC群峦传说的锻造公式图形化计算器，在java1.8下编写，内置了群峦传说:次世代中所有锻造品的相关信息，可通过jar或exe运行。

## 程序界面

![](img\main.png "主程序")

## 如何获取目标值

可以通过在游戏内载入""下的""资源包，以准确获取目标值。该资源包版本为1.18。

游戏内资源包的运行效果如下所示

![](img\game.png "资源包界面")

## 如何添加锻造项目

### 添加锻造项目数据

在项目源码内的""下的""可以增加相应文本行即可，注意文本内总行数不能超过54。

文本内项目属性为

>图片名 名称 右锻造步骤号 中锻造步骤号 左锻造步骤号 右步骤号 中步骤号 左步骤号 基底金属号

>锻造步骤号
>
>1 轻击
>
>2 击打
>
>3 冲压
>
>4 弯曲
>
>5 重击
>
>6 牵拉
>
>7 镦锻
>
>8 收缩

>步骤号
>
>1 倒数第三
>
>2 倒数第二
>
>3 末尾
>
>4 非最末
>
>5 任意

>基底金属号
>
>1 任意锭
>
>2 任意双锭
>
>3 任意薄板
>
>4 任意双层薄板
>
>5 生铁方坯
>
>6 精铁方坯
>
>7 高碳任意锭
>
>8 锻铁薄板
>
>9 锻铁双层薄板
>
>10 红/蓝钢锭

### 添加锻造项目图片

在项目源码内的""下增添相应16*16的png格式图片，图片名称需与""中图片名保持一致。

### 添加基底金属

暂时无法添加