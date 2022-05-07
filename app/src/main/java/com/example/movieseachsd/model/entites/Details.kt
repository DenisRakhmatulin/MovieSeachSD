package com.example.movieseachsd.model.entites


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Details(
    val movie: Movie = getDefaultMovie(),
    val release_date: String? = "1999-10-12",
    val overview: String? = "Описание отсутствует",
    val genre: String? = "Наверное очень многожанровый",
    val runtime: Int? = 0
) : Parcelable

fun getDefaultMovie() = Movie(550, "http://image.tmdb.org/t/p/w500/66RvLrRJTm4J8l3uHXWF09AICol.jpg", "Fighting club", 8.4)

fun getMovies() = listOf(
    Details(
        Movie(551, "@drawable/movie_sample_pic", "Death on the Nile", 5.5),
        "2022-04-02",
        "Отдых бельгийского сыщика Эркюля Пуаро в Египте на борту гламурного речного парохода превращается в ужасающие поиски убийцы, когда идиллический медовый месяц идеальной пары трагически обрывается. Эта история о безудержной страсти и безудержной ревности, действие ...",
        "Detective",
        129
    ),
    Details(
        Movie(552, "@drawable/movie_sample_pic", "Moonfall", 6.5),
        "2022-01-30",
        "По неизвестной причине Луна вдруг сходит с орбиты и идёт на столкновение с Землёй, что провоцирует множественные катаклизмы.",
        "Fiction",
        95
    ),
    Details(
        Movie(553, "@drawable/movie_sample_pic", "Spider-Man: No Way Home", 8.8),
        "2022-03-20",
        "Жизнь и репутация Питера Паркера оказываются под угрозой, поскольку Мистерио раскрыл всему миру тайну личности Человека-паука. Пытаясь исправить ситуацию, Питер обращается за помощью к Стивену Стрэнджу, но вскоре всё становится намного опаснее.",
        "Fiction",
        119
    ),
    Details(
        Movie(554, "@drawable/movie_sample_pic", "King Richard", 4.9),
        "2021-10-11",
        "Ричард Уильямс — отец теннисисток Серены и Винус Уильямс. Сам он практически никогда не играл в теннис и мало понимал в тренировке профессиональных теннисистов. Он планировал тренировать всех дочерей, но настоящий талант показали только Серена и Винус.",
        "Historical",
        98
    ),
    Details(
        Movie(555, "@drawable/movie_sample_pic", "The Batman", 7.0),
        "2022-04-04",
        "Когда Риддлер, серийный убийца-садист, начинает убивать ключевых политических деятелей Готэма, Бэтмен вынужден расследовать скрытую коррупцию в городе и сомневаться в причастности своей семьи...",
        "Fiction",
        145
    ),
    Details(
        Movie(556, "@drawable/movie_sample_pic", "Avatar", 8.9),
        "2009-06-07",
        "Джейк Салли — бывший морской пехотинец, прикованный к инвалидному креслу. Несмотря на немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание совершить путешествие в несколько световых лет к базе землян на планете Пандора, где корпорации добывают редкий минерал, имеющий огромное значение для выхода Земли из энергетического кризиса.",
        "Fiction",
        180
    ),
    Details(
        Movie(557, "@drawable/movie_sample_pic", "The Avengers", 8.6),
        "2012-05-03",
        "Локи, сводный брат Тора, возвращается, и в этот раз он не один. Земля на грани порабощения, и только лучшие из лучших могут спасти человечество.",
        "Fiction",
        175
    ),
    Details(
        Movie(558, "@drawable/movie_sample_pic", "Oblivion", 5.6),
        "2013-08-25",
        "Земля, пережившая войну с инопланетными захватчиками, опустела; остатки человечества готовятся покинуть непригодную для жизни планету. Главный герой — техник по обслуживанию дронов — находит разбившийся корабль NASA, команда которого погибает у него на глазах. Ему удаётся спасти лишь одну женщину — и вскоре он понимает, что это перевернёт его жизнь.",
        "Fiction",
        90
    ),
    Details(
        Movie(595, "@drawable/movie_sample_pic", "Furious 6", 6.5),
        "2013-01-15",
        "После того как Доминик и Брайн побывали в Рио, где они ограбили и свергли империю вора в законе, их команда получила 100 миллионов, и наши герои оказались разбросаны по всему миру. Но их привычка вечно жить в бегах не даёт им покоя.",
        "Action",
        106
    ),
    Details(
        Movie(566, "@drawable/movie_sample_pic", "Life of Pi", 5.9),
        "2012-04-01",
        "Это история сына владельца одного индийского зоопарка, мальчика по имени Пи. Он познаёт мир, учится отстаивать свои принципы, ищет собственную дорогу к Богу, живя по канонам трёх конфессий, влюбляется...",
        "Adventures",
        106
    )
)
