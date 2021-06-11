import riak

AUTHOR = 's23455'
DOC1_KEY = 'doc1'


def main():
    client = riak.RiakClient()
    bucket = client.bucket(AUTHOR)
    doc = {"field_1": "ala ma kota", "field_2": False, "field_3": 42}
    bucket.new(DOC1_KEY, doc).store()
    retrieved_document = bucket.get(DOC1_KEY)
    print(retrieved_document.data)
    doc["field_1"] = "ala NIE MA kota bo ma psa"
    bucket.new(DOC1_KEY, doc).store()
    updated_document = bucket.get(DOC1_KEY)
    print(updated_document.data)
    bucket.delete(DOC1_KEY)
    deleted_document = bucket.get(DOC1_KEY)
    print(deleted_document.data)


if __name__ == '__main__':
    main()
