Терміни:
    registry - місце, де зберігаються репозиторії (dockerHub)

Команди для контейнерів:
    * docker run -p 5050:5050 repoName/artifactName:version
        -p - мапінг порта комп'ютера до контейнера. hostPort:containerPort
        -d - ранить контейнер в detach Mode, тобто консоль не буде заблокована. Вертає id контейнера
        --restart=always/no - вказує, чи при рестарті docker daemon треба піднімати контейнер. Якщо always, то при рестарті докера контейнер підніметься автоматично
        -m 512m - вказує, що контейнер може використовувати не більше 512 мб оперативки
        --cpu-quota 5000 - вказує, що контейнер може використовувати не більше 5% CPU. Чим більше присвоюєш, тим більше споживає. Але й стає контейнер швидше працює
    * docker logs container_id - показує логи контейнера
        -f логи будуть доатачуватись в реальному часі
    * docker container ls - Вертає список усіх працюючих контейнерів
        -а показує також стопнуті контейнери
    * docker container pause container_id - ставить на паузу контейнер
    * docker container unpause container_id - знімає контейнер з паузи
    * docker container inspect container_id - вертає інформацію про контейнер
    * docker container prune - видаляє усі стопнуті контейнери
    * docker container stop container_id - зупиняє контейнер. Різниця між stop / pause в тому, що stop посуті закінчує усі процеси в контейнері і дає час на це
    * docker container kill container_id - схоже на stop, Але термінейтить процеси відразу без часу на завершення

Команди для імеджів:
    * docker images - вертає список усіх скачаних імеджів
    * docker tag some_tag - додає імеджу тег. Створить копію існуючого імеджу, але з іншим тегом
    * docker pull image_name tag - стягує потрібний імедж з registry
    * docker search name - робить пошук в registry по імені
    * docker image history image_id - показує, які степи треба зробити, щоб підняти контейнер з імеджа
    * docker image inspect image_id - показує метаінформацію про імедж
    * docker image remove image_id - видаляє імедж з локальної машини

Команди для інформації:
    * docker events - виводить на екран усі події, які відбуваються в докері
    * docker stats - показує викорситання ресурсів кожним контейнером

Архітектура:
    docker client - docker desktop та cli
    docker daemon - увесь движок, який займається підняттям контейнерів, сторенням, пуленням, пушенням імеджів

Білд своїх імеджів:
    За замоввчуванням можна білдати імеджі за допомогою maven plugin. Також можна додати певні конфіги для імеджа
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <!-- Ось цей конфіг потрібен для того, щоб створити image. pullPolicy вказує, коли потрібно тягнути
                     залежні images. По дефолту щоразу при створенні імеджа з регістрі буде тягнутись потрібні імеджі,
                     Якщо поставити IF_NOT_PRESENT, то буде тягнутись, якщо імеджа немає локально
                -->
                <configuration>
                    <image>
                        <name>super0lezhka/microtraining-${project.artifactId}:${project.version}</name>
                    </image>
                    <pullPolicy>IF_NOT_PRESENT</pullPolicy>
                </configuration>
            </plugin>
        </plugins>
    </build>