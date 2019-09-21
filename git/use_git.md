# use git

一、常用命令

  - 初始化仓库

    `git init`

  - 查看仓库的状态

    `git status`

  - 向暂存区中添加文件

    `git add`

  - 保存仓库的历史记录

    `git commit`

  - 查看提交的日志

    ```
    git log
    git log --pretty=short    # 只显示提交信息的第一行
    git log -p README.md      #  显示文件的改动
    git log --graph           #  以图表形式查看分支
    git reflog                #  查看当前仓库的操作日志
    
    ```

  - 查看工作树和暂存区的差别

    `git diff`

二、分支的操作

- 显示分支一览表

  `git branch`            ＃ 显示分支一览表, 左侧标有 “＊” ，表示这是我们当前所在的分支

  ```
  # git branch
  * feature-A
    master
  ```

- `git checkout -b`  创建／切换分支

  从master 分支创建feature-A 和 fix-B 分支

  `git  checkout -b feature-A`     # 切换到 feature-A 分支进行提交

  ```
  # git branch
  * feature-A
    master
  ```

  等同于下列命令：

  ```
  git branch feature-A                    # 创建分支
  git checkout feature-A                  # 切换分支
  ```

三、合并分支

- 想要合并分支，首先得切换到 master  分支

  `git checkout  master`

- 然后合并 feature-A 分支，为了在历史记录中明确记录下本次分支合并，我们需要创建合并提交。在合并时加上 `--no-ff`

  `git merge --no-ff feature-A`

- 以图表形式查看分支

  `git log  --graph`

四、更改提交的操作

- 回溯历史版本

  > 我们创建 fix-B  的特性分支，回溯到上一次提交。

  ```
  git checkout -b fix-B  # 创建 fix-B 分支
  git reflog          # 查看当前仓库的操作日志
  git checkout master # 回到主干分支
  git reset --hard 602f3aa  # 回到哈希值为 602f3aa
  ```

  

- 

五、 