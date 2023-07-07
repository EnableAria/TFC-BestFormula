# TFC BestFormula

## 简介

TFC群峦传说的锻造公式图形化计算器，利用java1.8编写，内置了 群峦传说:次世代 中所有锻造品的相关信息。

下载 [Releases](https://github.com/EnableAria/TFC-BestFormula/releases) 中的 exe文件 或 jar文件 以运行。

## 程序界面

![](/img/main.png "主程序")

## 如何获取目标值

可以通过在游戏内载入 [TFC-Forging-Assistance.zip](https://github.com/EnableAria/TFC-BestFormula/releases/tag/resourcepacks) 资源包，以准确获取目标值。该资源包支持的游戏版本为 1.18。

游戏内资源包的效果如下所示:

![](/img/game.png "资源包界面")

## 如何添加锻造项目

在进行下面步骤之前，你需要在工具所在目录下，创建 "config"文件夹 和 "image"文件夹。

### 添加锻造项目数据文件

在 "config"文件夹 内，创建 "forging.txt"文本文档文件 ，并在文档内添加锻造项目属性。

文档内锻造项目属性格式为:

>图片名 名称 右锻造步骤号 中锻造步骤号 左锻造步骤号 右步骤号 中步骤号 左步骤号 基底金属名

例子如下:

```
sheet 薄板 2 2 2 1 2 3 ingot
refined_bloom 精铁方坯 2 2 2 1 2 3 test
```

锻造步骤号:

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

步骤号:

>1 倒数第三
>
>2 倒数第二
>
>3 末尾
>
>4 非最末
>
>5 任意

基底金属名:

>unknown 不明
>
>ingot 任意锭
>
>double_ingot 任意双锭
>
>sheet 任意薄板
>
>double_sheet 任意双层薄板
>
>unrefined_bloom 生铁方坯
>
>refined_bloom 精铁方坯
>
>high_carbon_steel_ingot 高碳任意锭
>
>wrought_iron_sheet 锻铁薄板
>
>wrought_iron_double_sheet 锻铁双层薄板
>
>red_or_blue_steel_sheet 红/蓝钢锭

### 添加基底金属数据文件

在 "config"文件夹 内，创建 "need_metal.txt"文本文档文件 ，并在文档内添加基底金属属性即可。

文档内基底金属属性格式为:

>图片名 名称

例子如下:

```
ingot 任意锭
test 测试
```

### 添加锻造项目图片

在 "image"文件夹 内，增添相应的png格式图片，图片名称必须与 "forging.txt" 中图片名保持一致。

### 添加基底金属图片

在 "image"文件夹 内，增添相应的png格式图片，图片名称必须与 "need_metal.txt" 中图片名保持一致。