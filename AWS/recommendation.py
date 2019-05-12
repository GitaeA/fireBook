import numpy as np
import pandas as pd
import json
import psycopg2

# AWS RDS connect
conn_string ="host='bookingdata.c7bt9br99pg3.ap-northeast-2.rds.amazonaws.com' dbname ='Booking' user='gitae' password='booking123'"
conn = psycopg2.connect(conn_string)
cur = conn.cursor()

# Load books data
cur.execute("select * from books")
books_table = cur.fetchall()

# data -> DataFrame
books = pd.DataFrame(books_table)
books.columns = [desc[0] for desc in cur.description]

# data filtering
books = books[['book_id', 'title', 'language' ]]
books = books[books['language'] == 'eng']

# Load ratings data
cur.execute("select * from ratings")
ratings_table = cur.fetchall()

# data -> DataFrame
ratings = pd.DataFrame(ratings_table)
ratings.columns = [desc[0] for desc in cur.description]

# data Cleansing
books.book_id = pd.to_numeric(books.book_id, errors='coerce')
ratings.book_id = pd.to_numeric(ratings.book_id, errors='coerce')


join_data = pd.merge(ratings, books, on='book_id', how='inner')

join_data.rating.astype(float)
join_data.rating = pd.to_numeric(join_data.rating, errors='coerce')

# Create Piviot table
matrix = join_data[0:30000].pivot_table(index='user_id', columns='title', values='rating')

# Pearson Corrleation
def pearsonCor(x, y):
    SSx = x - x.mean()
    SSy = y - y.mean()
    return np.sum(SSx * SSy) / np.sqrt(np.sum(SSx ** 2) * np.sum(SSy ** 2))


# Recoomend
def recommend(input_book, matrix, n):

    result = []
    for title in matrix.columns:
        if title == input_book:
            continue

        # rating comparison
        cor = pearsonCor(matrix[input_book], matrix[title])

        if np.isnan(cor):
            continue
        else:
            result.append((title, '{:.2f}'.format(cor)))



    result.sort(key=lambda r: r[1], reverse=True)

    return result[:n]

recommend_result = recommend('1776', matrix, 10)

# Json conversion
json_result = json.dumps(dict(recommend_result))

print(json_result)